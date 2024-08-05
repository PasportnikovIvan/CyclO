package dev.bikeService.service;

import dev.bikeService.entity.Bike;

import java.util.List;
import java.util.UUID;

public interface BikeService {
    List<Bike> getAllBikes();
    Bike getBikeById(UUID id);
    Bike createBike(Bike bike);
    void deleteBike(UUID id);
}
