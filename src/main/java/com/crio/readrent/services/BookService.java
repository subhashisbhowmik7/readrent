package com.crio.readrent.services;

import com.crio.readrent.entities.Book;
import com.crio.readrent.exceptions.BookNotFoundException;
import com.crio.readrent.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found for this id :: " + id));
        
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setGenre(bookDetails.getGenre());
        book.setAvailabilityStatus(bookDetails.getAvailabilityStatus());

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found for this id :: " + id));
        bookRepository.delete(book);
    }
}
