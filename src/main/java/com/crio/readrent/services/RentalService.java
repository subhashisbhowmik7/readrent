package com.crio.readrent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.readrent.entities.*;
import com.crio.readrent.exceptions.BookNotFoundException;
import com.crio.readrent.repositories.BookRepository;
import com.crio.readrent.repositories.RentalRepository;
import com.crio.readrent.repositories.UserRepository;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public Rental rentBook(Long bookId, Long userId) {
        if (rentalRepository.countByUserId(userId) >= 2) {
            throw new IllegalArgumentException("User cannot rent more than two books.");
        }

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found for this id :: " + bookId));

        if (book.getAvailabilityStatus().equals(AvailabilityStatus.NOT_AVAILABLE)) {
            throw new IllegalArgumentException("Book is not available for rent.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BookNotFoundException("User not found for this id :: " + userId));

        book.setAvailabilityStatus(AvailabilityStatus.NOT_AVAILABLE);
        bookRepository.save(book);

        Rental rental = new Rental();
        rental.setBook(book);
        rental.setUser(user);

        return rentalRepository.save(rental);
    }

    public Rental returnBook(Long bookId, Long userId) {
        Rental rental = rentalRepository.findByBookIdAndUserId(bookId, userId)
                .orElseThrow(() -> new BookNotFoundException("Rental not found for this book id and user id"));

        rentalRepository.delete(rental);

        Book book = rental.getBook();
        book.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        bookRepository.save(book);

        return rental;
    }
}