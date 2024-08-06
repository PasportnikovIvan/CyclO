package dev.bikeService.repository;

import dev.bikeService.entity.BikeParkingStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
/**
 * Repository interface for BikeParkingStation entities.
 */
public interface BikeParkingStationRepository extends JpaRepository<BikeParkingStation, UUID> {
    BikeParkingStation findByLocationAddress(String locationAddress);
    List<BikeParkingStation> findByCapacityGreaterThanEqual(int capacity);
}
