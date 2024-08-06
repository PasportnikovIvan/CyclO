package dev.bikeService.controller;

import dev.bikeService.dto.BikeDTO;
import dev.bikeService.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST controller for managing bike-related operations.
 */
@RestController
@RequestMapping("/bikes")
public class BikeController {

    private final BikeService bikeService;

    @Autowired
    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    /**
     * Gets a list of all bikes.
     *
     * @return a list of {@link BikeDTO}
     */
    @GetMapping
    public List<BikeDTO> getAllBikes() {
        return bikeService.getAllBikes();
    }
    /**
     * Gets a bike by its ID.
     *
     * @param id the ID of the bike
     * @return the {@link BikeDTO}
     */
    @GetMapping("/{id}")
    public BikeDTO getBikeById(@PathVariable Long id) {
        return bikeService.getBikeById(id);
    }
    /**
     * Creates a new bike.
     *
     * @param bikeDTO the bike details
     * @return the created {@link BikeDTO}
     */
    @PostMapping
    public BikeDTO createBike(@RequestBody BikeDTO bikeDTO) {
        return bikeService.createBike(bikeDTO);
    }
    /**
     * Updates a bike data by bike ID.
     *
     * @param id the ID of the bike to update
     */
    @PutMapping("/{id}")
    public BikeDTO updateBike(@PathVariable Long id, @RequestBody BikeDTO bikeDTO) {
        return bikeService.updateBike(id, bikeDTO);
    }
    /**
     * Deletes a bike by its ID.
     *
     * @param id the ID of the bike to delete
     */
    @DeleteMapping("/{id}")
    public void deleteBike(@PathVariable Long id) {
        bikeService.deleteBike(id);
    }
}