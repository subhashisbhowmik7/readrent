package com.crio.readrent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crio.readrent.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
