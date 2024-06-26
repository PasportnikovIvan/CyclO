package dev.rentalService.service;

import dev.rentalService.entity.RentalRecord;
import dev.rentalService.repository.RentalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    private RentalRecordRepository rentalRecordRepository;

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
}
