package com.crio.readrent.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {  
        super(message);
    }
    
}
