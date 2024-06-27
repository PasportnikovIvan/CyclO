package dev.rentalService.controller;

import dev.rentalService.entity.RentalRecord;
import dev.rentalService.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping
    public List<RentalRecord> getAllRentalRecords() {
        return rentalService.getAllRentalRecords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalRecord> getRentalRecordById(@PathVariable Long id) {
        return rentalService.getRentalRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @PostMapping
//    public RentalRecord createRentalRecord(@Valid @RequestBody RentalRecord rentalRecord) {
//        return rentalService.createRentalRecord(rentalRecord);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRentalRecord(@PathVariable Long id) {
        rentalService.deleteRentalRecord(id);
        return ResponseEntity.noContent().build();
    }
}
