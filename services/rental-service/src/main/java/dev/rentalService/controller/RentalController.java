package dev.rentalService.controller;

import dev.rentalService.entity.RentalRecord;
import dev.rentalService.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rents")
public class RentalController {

    @Autowired
    private RentalService rentalService;
}