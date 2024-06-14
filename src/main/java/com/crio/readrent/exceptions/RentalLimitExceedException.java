package com.crio.readrent.exceptions;


public class RentalLimitExceedException extends RuntimeException {
    public RentalLimitExceedException(String message) {
        super(message);
    }
}
