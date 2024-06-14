package com.crio.readrent.exceptions;

public class RentalNotFoundException extends RuntimeException{

    public RentalNotFoundException(String message) {
        super(message);
    }
    
}
