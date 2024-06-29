package dev.warehouseService.repository;

import dev.warehouseService.entity.BikePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikePartRepository extends JpaRepository<BikePart, Long> {
}