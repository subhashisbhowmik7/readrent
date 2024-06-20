package com.crio.readrent.entities;

import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Table(name = "book")
@AllArgsConstructor
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Rental> getRentals() {
        return this.rentals;
    }

    public void setRentals(Set<Rental> rentals) {
        this.rentals = rentals;
    }
    private String author;

    @OneToMany(mappedBy = "book")
    private Set<Rental> rentals;
    
    public Long getBookId() {
        return this.id;
    }

    public void setBookId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public AvailabilityStatus getAvailabilityStatus() {
        return this.availabilityStatus;
    }


    @Column(nullable = false)
    private String genre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AvailabilityStatus availabilityStatus = AvailabilityStatus.AVAILABLE;

    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

}
