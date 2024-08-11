package dev.rentalService.service.impl;

import dev.rentalService.dto.RentalDTO;
import dev.rentalService.entity.RentalRecord;
import dev.rentalService.exception.ResourceNotFoundException;
import dev.rentalService.kafka.MessageProducer;
import dev.rentalService.mapper.RentalMapper;
import dev.rentalService.repository.RentalRecordRepository;
import dev.rentalService.repository.UserRepository;
import dev.rentalService.service.RentalService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shared.CustomLoggerFactory;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for managing rental records.
 * Provides methods for creating, updating, retrieving, and deleting rental records,
 * as well as starting and ending rentals.
 */
@Service
public class RentalServiceImpl implements RentalService {

    private MessageProducer messageProducer;
    private RentalRecordRepository rentalRecordRepository;
    private UserRepository userRepository;
    private static CustomLoggerFactory CustomLoggerFactory;
    private final RentalMapper rentalMapper;

    private static final Logger logger = CustomLoggerFactory.getLogger(RentalServiceImpl.class);

    /**
     * Constructs a new RentalServiceImpl with the specified repositories, message producer, and mapper.
     *
     * @param rentalRecordRepository the repository for rental records
     * @param userRepository the repository for users
     * @param messageProducer the producer for sending messages
     * @param rentalMapper the mapper for converting between entities and DTOs
     */
    @Autowired
    public RentalServiceImpl(RentalRecordRepository rentalRecordRepository,
                             UserRepository userRepository,
                             MessageProducer messageProducer,
                             RentalMapper rentalMapper) {
        this.rentalRecordRepository = rentalRecordRepository;
        this.userRepository = userRepository;
        this.messageProducer = messageProducer;
        this.rentalMapper = rentalMapper;
    }

    /**
     * Retrieves all rental records.
     *
     * @return a list of RentalDTO objects representing all rental records
     */
    @Override
    public List<RentalDTO> getAllRentalRecords() {
        return rentalRecordRepository.findAll().stream()
                .map(rentalMapper::rentalToRentalDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a rental record by its ID.
     *
     * @param rentalId the ID of the rental record to retrieve
     * @return the RentalDTO representing the retrieved rental record
     * @throws ResourceNotFoundException if no rental record is found with the given ID
     */
    @Override
    public RentalDTO getRentalRecordById(Long rentalId) {
        RentalRecord rentalRecord = rentalRecordRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id " + rentalId));
        return rentalMapper.rentalToRentalDTO(rentalRecord);
    }

    /**
     * Creates a new rental record.
     *
     * @param rentalDTO the RentalDTO representing the rental record to create
     * @return the RentalDTO representing the created rental record
     */
    @Override
    public RentalDTO createRentalRecord(RentalDTO rentalDTO) {
        RentalRecord rentalRecord = rentalMapper.rentalDTOToRental(rentalDTO);
        RentalRecord savedRental = rentalRecordRepository.save(rentalRecord);
        return rentalMapper.rentalToRentalDTO(savedRental);
    }

    /**
     * Updates an existing rental record.
     *
     * @param rentalId the ID of the rental record to update
     * @param rentalDetails the RentalDTO containing the updated rental details
     * @return the RentalDTO representing the updated rental record
     * @throws ResourceNotFoundException if no rental record is found with the given ID
     */
    @Override
    public RentalDTO updateRentalRecord(Long rentalId, RentalDTO rentalDetails) {
        RentalRecord rentalRecord = rentalRecordRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id " + rentalId));
        rentalRecord.setUserId(rentalDetails.getUserId());
        rentalRecord.setBikePartId(rentalDetails.getBikePartId());
        rentalRecord.setIsActive(rentalDetails.isActive());
        RentalRecord updatedRental = rentalRecordRepository.save(rentalRecord);
        return rentalMapper.rentalToRentalDTO(updatedRental);
    }

    /**
     * Deletes a rental record by its ID.
     *
     * @param rentalId the ID of the rental record to delete
     * @throws ResourceNotFoundException if no rental record is found with the given ID
     */
    @Override
    public void deleteRentalRecord(Long rentalId) {
        RentalRecord rentalRecord = rentalRecordRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id " + rentalId));
        rentalRecordRepository.delete(rentalRecord);
    }

    /**
     * Starts a rental for a user and a specific bike part.
     *
     * @param userId the user ID who is renting
     * @param bikePartId the bike part ID being rented
     */
    @Override
    @Transactional
    public void startRental(Long userId, Long bikePartId) {
        RentalRecord record = new RentalRecord();
        record.setUserId(userId);
        record.setBikePartId(bikePartId);
        record.setIsActive(true);
        rentalRecordRepository.save(record);

        String startMessage = String.format("Rental started: User %d, BikePart %d", userId, bikePartId);
        messageProducer.sendMessage("rental-topic", startMessage);

        logger.info(startMessage);
    }

    /**
     * Ends a rental by setting it as inactive.
     *
     * @param rentalId the ID of the rental to end
     * @throws IllegalArgumentException if the rental ID is invalid
     */
    @Override
    @Transactional
    public void endRental(Long rentalId) {
        RentalRecord record = rentalRecordRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rental ID: " + rentalId));
        record.setIsActive(false);
        rentalRecordRepository.save(record);

        String endMessage = String.format("Rental ended: Rental ID %d", rentalId);
        messageProducer.sendMessage("rental-topic", endMessage);

        logger.info(endMessage);
    }
}
