package com.crio.readrent.entities;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


}
