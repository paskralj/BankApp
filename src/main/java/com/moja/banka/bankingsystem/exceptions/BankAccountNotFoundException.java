package com.moja.banka.bankingsystem.exceptions;

public class BankAccountNotFoundException extends RuntimeException{
    public BankAccountNotFoundException(String message) {
        super(message);
    }
    public BankAccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
