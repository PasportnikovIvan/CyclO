package dev.bikeService.service;

import dev.bikeService.entity.Bike;
import dev.bikeService.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BikeService {

    private final BikeRepository bikeRepository;

    @Autowired
    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public List<Bike> getAllBikes() {
        return (List<Bike>) bikeRepository.findAll();
    }

    public Bike getBikeById(UUID id) {
        return bikeRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Bike not found with id: " + id));
    }

    public Bike createBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    public void deleteBike(UUID id) {
        bikeRepository.deleteById(String.valueOf(id));
    }
}
