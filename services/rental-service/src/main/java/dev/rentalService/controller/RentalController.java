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

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<RentalDTO> getAllRentalRecords() {
        return rentalService.getAllRentalRecords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDTO> getRentalRecordById(@PathVariable Long id) {
        RentalDTO rentalDTO = rentalService.getRentalRecordById(id);
        return ResponseEntity.ok(rentalDTO);
    }

    @PostMapping
    public ResponseEntity<RentalDTO> createRentalRecord(@RequestBody RentalDTO rentalDTO) {
        RentalDTO createdRental = rentalService.createRentalRecord(rentalDTO);
        return ResponseEntity.ok(createdRental);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalDTO> updateRentalRecord(@PathVariable Long id, @RequestBody RentalDTO rentalDTO) {
        RentalDTO updatedRental = rentalService.updateRentalRecord(id, rentalDTO);
        return ResponseEntity.ok(updatedRental);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRentalRecord(@PathVariable Long id) {
        rentalService.deleteRentalRecord(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/start")
    public ResponseEntity<Void> startRental(@RequestParam Long userId, @RequestParam Long bikePartId) {
        rentalService.startRental(userId, bikePartId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/end/{id}")
    public ResponseEntity<Void> endRental(@PathVariable Long id) {
        rentalService.endRental(id);
        return ResponseEntity.noContent().build();
    }
}
