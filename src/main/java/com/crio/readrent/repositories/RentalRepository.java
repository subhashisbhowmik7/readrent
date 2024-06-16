package com.crio.readrent.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.readrent.entities.Book;
import com.crio.readrent.entities.Rental;
import com.crio.readrent.entities.User;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUser(User user);
    Optional<Rental> findByUserAndBook(User user, Book book);
}