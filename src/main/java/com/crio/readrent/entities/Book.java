package com.crio.readrent.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Table(name = "books")
@AllArgsConstructor
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String genre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AvailabilityStatus availabilityStatus = AvailabilityStatus.AVAILABLE;

    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

}
