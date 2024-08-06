package dev.rentalService.service;

import dev.rentalService.dto.RentalDTO;
import java.util.List;

public interface RentalService {
    List<RentalDTO> getAllRentalRecords();
    RentalDTO getRentalRecordById(Long rentalId);
    RentalDTO createRentalRecord(RentalDTO rentalDTO);
    RentalDTO updateRentalRecord(Long rentalId, RentalDTO rentalDetails);
    void deleteRentalRecord(Long rentalId);
    void startRental(Long userId, Long bikePartId);
    void endRental(Long rentalId);
}
