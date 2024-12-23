package com.moja.banka.bankingsystem.exceptions;

public class OibException extends RuntimeException{

    public OibException(String message) {
        super(message);
    }

    public OibException(String message, Throwable cause) {
        super(message, cause);
    }
}
