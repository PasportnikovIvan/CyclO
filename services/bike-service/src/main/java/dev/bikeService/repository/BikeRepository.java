package dev.bikeService.repository;

import dev.bikeService.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository interface for Bike entities.
 */
@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {
}