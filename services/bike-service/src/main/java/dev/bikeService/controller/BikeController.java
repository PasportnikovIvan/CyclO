package dev.bikeService.controller;

import dev.bikeService.entity.Bike;
import dev.bikeService.service.impl.BikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bikes")
public class BikeController {

    @Autowired
    private BikeServiceImpl bikeServiceImpl;

    @GetMapping
    public ResponseEntity<List<Bike>> getAllBikes() {
        return ResponseEntity.ok(bikeServiceImpl.getAllBikes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable UUID id) {
        return ResponseEntity.ok(bikeServiceImpl.getBikeById(id));
    }

    @PostMapping
    public ResponseEntity<Bike> createBike(@RequestBody Bike bike) {
        return ResponseEntity.ok(bikeServiceImpl.createBike(bike));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBike(@PathVariable UUID id) {
        bikeServiceImpl.deleteBike(id);
        return ResponseEntity.noContent().build();
    }
}
