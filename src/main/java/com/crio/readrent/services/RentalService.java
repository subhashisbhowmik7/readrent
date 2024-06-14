package com.crio.readrent.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.crio.readrent.entities.*;
import com.crio.readrent.exceptions.BookNotFoundException;
import com.crio.readrent.exceptions.RentalLimitExceedException;
import com.crio.readrent.exceptions.RentalNotFoundException;
import com.crio.readrent.repositories.BookRepository;
import com.crio.readrent.repositories.RentalRepository;
import com.crio.readrent.repositories.UserRepository;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public void rentBook(Long bookId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);

        List<Rental> activeRentals = rentalRepository.findActiveRentalsByUserId(user.getUserId());
        if (activeRentals.size() >= 2) {
            throw new RentalLimitExceedException("User cannot have more than two active rentals.");
        }

        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new BookNotFoundException("Book not found with id " + bookId));
        if (book.getAvailabilityStatus() == AvailabilityStatus.RENTED) {
            throw new Exception("Book is already rented.");
        }

        Rental rental = new Rental();
        rental.setBook(book);
        rental.setUser(user);
        rentalRepository.save(rental);

        book.setAvailabilityStatus(AvailabilityStatus.RENTED);
        bookRepository.save(book);
    }

    public void returnBook(Long bookId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);

        Rental rental = rentalRepository.findByUserIdAndBookIdAndReturnDateIsNull(user.getUserId(), bookId)
            .orElseThrow(() -> new RentalNotFoundException("Rental not found for book id " + bookId + " and user id " + user.getUserId()));
        
        rentalRepository.save(rental);

        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new BookNotFoundException("Book not found with id " + bookId));
        book.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        bookRepository.save(book);
    }
}

