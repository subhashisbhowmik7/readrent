package com.crio.readrent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crio.readrent.entities.Rental;
import com.crio.readrent.services.RentalService;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping("/rent/{bookId}")
    public ResponseEntity<Rental> rentBook(@PathVariable Long bookId, @RequestBody Long userId) {
        return ResponseEntity.ok(rentalService.rentBook(bookId, userId));
    }

    @PostMapping("/return/{bookId}")
    public ResponseEntity<Rental> returnBook(@PathVariable Long bookId, @RequestBody Long userId) {
        return ResponseEntity.ok(rentalService.returnBook(bookId, userId));
    }
}
