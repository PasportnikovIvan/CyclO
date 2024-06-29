package dev.rentalService.service;

import dev.rentalService.entity.RentalRecord;
import dev.rentalService.entity.User;
import dev.rentalService.kafka.MessageProducer;
import dev.rentalService.repository.RentalRecordRepository;
import dev.rentalService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {
    @Autowired
    private MessageProducer messageProducer;
    @Autowired
    private RentalRecordRepository rentalRecordRepository;
    @Autowired
    private UserRepository userRepository;

    public List<RentalRecord> getAllRentalRecords() {
        return rentalRecordRepository.findAll();
    }

    public Optional<RentalRecord> getRentalRecordById(Long id) {
        return rentalRecordRepository.findById(id);
    }

    public RentalRecord createRentalRecord(RentalRecord rentalRecord) {
        return rentalRecordRepository.save(rentalRecord);
    }

    public void deleteRentalRecord(Long id) {
        rentalRecordRepository.deleteById(id);
    }
    /**
     * Starts a rental for a user and a specific bike part.
     *
     * @param userId    the user ID who is renting
     * @param bikePartId the bike part ID being rented
     */
    @Transactional
    public void startRental(Long userId, Long bikePartId) {
        RentalRecord record = new RentalRecord();
        record.setUserId(userId);
        record.setBikePartId(bikePartId);
        record.setIsActive(true);
        rentalRecordRepository.save(record);

        String startMessage = String.format("Rental started: User %d, BikePart %d", userId, bikePartId);
        messageProducer.sendMessage("rental-topic", startMessage);

        System.out.println(startMessage);
    }

    /**
     * Ends a rental by setting it as inactive.
     *
     * @param rentalId the ID of the rental to end
     */
    @Transactional
    public void endRental(Long rentalId) {
        RentalRecord record = rentalRecordRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rental ID: " + rentalId));
        record.setIsActive(false);
        rentalRecordRepository.save(record);

        String endMessage = String.format("Rental ended: Rental ID %d", rentalId);
        messageProducer.sendMessage("rental-topic", endMessage);

        System.out.println(endMessage);
    }
}
