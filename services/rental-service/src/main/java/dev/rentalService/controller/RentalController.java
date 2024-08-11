package dev.rentalService.controller;

import dev.rentalService.dto.RentalDTO;
import dev.rentalService.entity.RentalRecord;
import dev.rentalService.service.RentalService;
import dev.rentalService.service.impl.RentalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for managing rental records.
 * Provides endpoints for creating, updating, retrieving, and deleting rental records,
 * as well as starting and ending rentals.
 */
@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;
    /**
     * Constructor for RentalController.
     *
     * @param rentalService the rental service to be used by this controller
     */
    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    /**
     * Retrieves all rental records.
     *
     * @return a list of RentalDTO objects representing all rental records
     */
    @GetMapping
    public List<RentalDTO> getAllRentalRecords() {
        return rentalService.getAllRentalRecords();
    }

    /**
     * Retrieves a rental record by its ID.
     *
     * @param id the ID of the rental record
     * @return a ResponseEntity containing the RentalDTO of the specified rental record
     */
    @GetMapping("/{id}")
    public ResponseEntity<RentalDTO> getRentalRecordById(@PathVariable Long id) {
        RentalDTO rentalDTO = rentalService.getRentalRecordById(id);
        return ResponseEntity.ok(rentalDTO);
    }

    /**
     * Creates a new rental record.
     *
     * @param rentalDTO the rental record to be created
     * @return a ResponseEntity containing the created RentalDTO
     */
    @PostMapping
    public ResponseEntity<RentalDTO> createRentalRecord(@RequestBody RentalDTO rentalDTO) {
        RentalDTO createdRental = rentalService.createRentalRecord(rentalDTO);
        return ResponseEntity.ok(createdRental);
    }

    /**
     * Updates an existing rental record.
     *
     * @param id the ID of the rental record to be updated
     * @param rentalDTO the updated rental record
     * @return a ResponseEntity containing the updated RentalDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<RentalDTO> updateRentalRecord(@PathVariable Long id, @RequestBody RentalDTO rentalDTO) {
        RentalDTO updatedRental = rentalService.updateRentalRecord(id, rentalDTO);
        return ResponseEntity.ok(updatedRental);
    }

    /**
     * Deletes a rental record by its ID.
     *
     * @param id the ID of the rental record to be deleted
     * @return a ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRentalRecord(@PathVariable Long id) {
        rentalService.deleteRentalRecord(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Starts a rental for a user with a specific bike part.
     *
     * @param userId the ID of the user starting the rental
     * @param bikePartId the ID of the bike part being rented
     * @return a ResponseEntity with no content
     */
    @PostMapping("/start")
    public ResponseEntity<Void> startRental(@RequestParam Long userId, @RequestParam Long bikePartId) {
        rentalService.startRental(userId, bikePartId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Ends a rental by its ID.
     *
     * @param id the ID of the rental to be ended
     * @return a ResponseEntity with no content
     */
    @PostMapping("/end/{id}")
    public ResponseEntity<Void> endRental(@PathVariable Long id) {
        rentalService.endRental(id);
        return ResponseEntity.noContent().build();
    }
}
