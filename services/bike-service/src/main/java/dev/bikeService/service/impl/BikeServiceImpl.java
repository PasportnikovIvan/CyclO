package dev.bikeService.service.impl;

import dev.bikeService.entity.Bike;
import dev.bikeService.repository.BikeRepositoryElasticsearch;
import dev.bikeService.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BikeServiceImpl implements BikeService {

    private final BikeRepositoryElasticsearch bikeRepositoryElasticsearch;

    @Autowired
    public BikeServiceImpl(BikeRepositoryElasticsearch bikeRepositoryElasticsearch) {
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
