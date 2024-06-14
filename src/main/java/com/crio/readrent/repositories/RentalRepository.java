package com.crio.readrent.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.crio.readrent.entities.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findActiveRentalsByUserId(Long userId);
    Optional<Rental> findByUserIdAndBookIdAndReturnDateIsNull(Long userId, Long bookId);
}