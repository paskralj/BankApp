package com.moja.banka.bankingsystem.controller;

import com.moja.banka.bankingsystem.dto.CreateAccountDTO;
import com.moja.banka.bankingsystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /*
    "oib" : "12321189019",
    "accountType" : "SAVINGS",
    "currency" : "EUR"
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createAccountForUser(@RequestBody CreateAccountDTO accountDto) {
        accountService.createAccountForUser(accountDto);
        return ResponseEntity.ok("Account created successfully!");
    }
}
