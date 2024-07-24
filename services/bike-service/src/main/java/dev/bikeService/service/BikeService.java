package dev.bikeService.service;

import dev.bikeService.entity.Bike;
import dev.bikeService.repository.BikeRepositoryElasticsearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BikeService {

    private final BikeRepositoryElasticsearch bikeRepositoryElasticsearch;

    @Autowired
    public BikeService(BikeRepositoryElasticsearch bikeRepositoryElasticsearch) {
        this.bikeRepositoryElasticsearch = bikeRepositoryElasticsearch;
    }

    public List<Bike> getAllBikes() {
        return (List<Bike>) bikeRepositoryElasticsearch.findAll();
    }

    public Bike getBikeById(UUID id) {
        return bikeRepositoryElasticsearch.findById(String.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Bike not found with id: " + id));
    }

    public Bike createBike(Bike bike) {
        return bikeRepositoryElasticsearch.save(bike);
    }

    public void deleteBike(UUID id) {
        bikeRepositoryElasticsearch.deleteById(String.valueOf(id));
    }
}
