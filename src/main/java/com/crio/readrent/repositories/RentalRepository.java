package com.crio.readrent.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.readrent.entities.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    long countByUserId(Long userId);
    Optional <Rental> findByBookIdAndUserId(Long bookId, Long userId);
}