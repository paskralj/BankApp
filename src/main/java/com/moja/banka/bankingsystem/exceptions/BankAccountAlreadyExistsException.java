package com.moja.banka.bankingsystem.exceptions;

public class BankAccountAlreadyExistsException extends RuntimeException{
    public BankAccountAlreadyExistsException(String message) {
        super(message);
    }
    public BankAccountAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
