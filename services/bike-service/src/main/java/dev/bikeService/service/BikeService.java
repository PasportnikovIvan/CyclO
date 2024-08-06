package dev.bikeService.service;

import dev.bikeService.dto.BikeDTO;
import dev.bikeService.entity.Bike;

import java.util.List;
import java.util.UUID;
/**
 * Service interface for managing bikes.
 */
public interface BikeService {
    List<BikeDTO> getAllBikes();
    BikeDTO getBikeById(Long id);
    BikeDTO createBike(BikeDTO bike);
    BikeDTO updateBike(Long id, BikeDTO bikeDetails);
    void deleteBike(Long id);
}
