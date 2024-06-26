package dev.bikeService.controller;

import dev.bikeService.entity.Bike;
import dev.bikeService.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bikes")
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAllBikes() {
        return ResponseEntity.ok(bikeService.getAllBikes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable UUID id) {
        return ResponseEntity.ok(bikeService.getBikeById(id));
    }

    @PostMapping
    public ResponseEntity<Bike> createBike(@RequestBody Bike bike) {
        return ResponseEntity.ok(bikeService.createBike(bike));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBike(@PathVariable UUID id) {
        bikeService.deleteBike(id);
        return ResponseEntity.noContent().build();
    }
}
