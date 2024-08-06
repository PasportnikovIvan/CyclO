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

@Service
public class RentalServiceImpl implements RentalService {

    private MessageProducer messageProducer;
    private RentalRecordRepository rentalRecordRepository;
    private UserRepository userRepository;
    private static CustomLoggerFactory CustomLoggerFactory;
    private final RentalMapper rentalMapper;

    private static final Logger logger = CustomLoggerFactory.getLogger(RentalServiceImpl.class);

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

    @Override
    public List<RentalDTO> getAllRentalRecords() {
        return rentalRecordRepository.findAll().stream()
                .map(rentalMapper::rentalToRentalDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RentalDTO getRentalRecordById(Long rentalId) {
        RentalRecord rentalRecord = rentalRecordRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id " + rentalId));
        return rentalMapper.rentalToRentalDTO(rentalRecord);
    }

    @Override
    public RentalDTO createRentalRecord(RentalDTO rentalDTO) {
        RentalRecord rentalRecord = rentalMapper.rentalDTOToRental(rentalDTO);
        RentalRecord savedRental = rentalRecordRepository.save(rentalRecord);
        return rentalMapper.rentalToRentalDTO(savedRental);
    }

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

    @Override
    public void deleteRentalRecord(Long rentalId) {
        RentalRecord rentalRecord = rentalRecordRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id " + rentalId));
        rentalRecordRepository.delete(rentalRecord);
    }

    /**
     * Starts a rental for a user and a specific bike part.
     *
     * @param userId    the user ID who is renting
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
