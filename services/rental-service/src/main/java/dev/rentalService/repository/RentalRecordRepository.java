package dev.rentalService.repository;

import dev.rentalService.entity.RentalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRecordRepository extends JpaRepository<RentalRecord, Long> {
}
