package com.moja.banka.bankingsystem.exceptions;

public class OibNotFoundException extends RuntimeException{

    public OibNotFoundException(String message) {
        super(message);
    }

    public OibNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
