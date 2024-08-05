package dev.bikeService.controller;

import dev.bikeService.dto.BikeDTO;
import dev.bikeService.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bikes")
public class BikeController {

    private final BikeService bikeService;

    @Autowired
    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping
    public List<BikeDTO> getAllBikes() {
        return bikeService.getAllBikes();
    }

    @GetMapping("/{id}")
    public BikeDTO getBikeById(@PathVariable Long id) {
        return bikeService.getBikeById(id);
    }

    @PostMapping
    public BikeDTO createBike(@RequestBody BikeDTO bikeDTO) {
        return bikeService.createBike(bikeDTO);
    }

    @PutMapping("/{id}")
    public BikeDTO updateBike(@PathVariable Long id, @RequestBody BikeDTO bikeDTO) {
        return bikeService.updateBike(id, bikeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBike(@PathVariable Long id) {
        bikeService.deleteBike(id);
    }
}