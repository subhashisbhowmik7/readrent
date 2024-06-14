package com.crio.readrent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crio.readrent.services.RentalService;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping("/rent/{bookId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> rentBook(@PathVariable Long bookId) {
        try {
            rentalService.rentBook(bookId);
            return new ResponseEntity<>("Book rented successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/return/{bookId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        try {
            rentalService.returnBook(bookId);
            return new ResponseEntity<>("Book returned successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}


