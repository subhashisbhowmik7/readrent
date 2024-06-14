package com.crio.readrent.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.readrent.entities.Book;
import com.crio.readrent.exceptions.BookNotFoundException;
import com.crio.readrent.repositories.BookRepository;
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long bookId, Book bookDetails) {
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new BookNotFoundException("Book not found with id " + bookId));
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setGenre(bookDetails.getGenre());
        book.setAvailabilityStatus(bookDetails.getAvailabilityStatus());
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new BookNotFoundException("Book not found with id " + bookId));
        bookRepository.delete(book);
    }
}
