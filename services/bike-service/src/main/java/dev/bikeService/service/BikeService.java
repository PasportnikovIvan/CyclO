package dev.bikeService.service;

import dev.bikeService.entity.Bike;
import dev.bikeService.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    public Bike getBikeById(UUID id) {
        return bikeRepository.findById(id).orElseThrow(() -> new RuntimeException("Bike not found"));
    }

    public Bike createBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    public void deleteBike(UUID id) {
        bikeRepository.deleteById(id);
    }
}