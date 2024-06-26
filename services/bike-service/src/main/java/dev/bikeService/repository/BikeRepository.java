package dev.bikeService.repository;

import dev.bikeService.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BikeRepository extends JpaRepository<Bike, UUID> {
    List<Bike> findByStationId(UUID stationId);
}

