package com.moja.banka.bankingsystem.controller;

import com.moja.banka.bankingsystem.dto.AuthResponseDTO;
import com.moja.banka.bankingsystem.dto.LoginDTO;
import com.moja.banka.bankingsystem.dto.UserRegistrationDTO;
import com.moja.banka.bankingsystem.entities.AccountEntity;
import com.moja.banka.bankingsystem.entities.UserEntity;
import com.moja.banka.bankingsystem.security.JwtGenerator;
import com.moja.banka.bankingsystem.services.AccountService;
import com.moja.banka.bankingsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final AccountService accountService;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtGenerator jwtGenerator
            , AccountService accountService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.accountService = accountService;
    }

    /*
    "firstName": "Aaa",
    "lastName": "Jjj",
    "email": "asd.asd@example.com",
    "phoneNumber": "+385982435678",
    "address": "123 Main Street, Zagreb, Croatia",
    "dateOfBirth": "2000-05-15",
    "username" : "aaa",
    "password": "aaa",
    "oib": "12321189019"
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserEntity user = userRegistrationDTO.toUserEntity();
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully! ");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @GetMapping("/oib/{oib}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getUserByOib(@PathVariable String oib) {
        UserEntity user = userService.findUserByOib(oib);
        return ResponseEntity.ok(String.format("There is a user %s %s with oib: %s ", user.getFirstName(), user.getLastName(), user.getOib()));
    }

    @GetMapping("/accounts/{oib}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getUserAccountsByOib(@PathVariable String oib) {
        UserEntity user = userService.findUserByOib(oib);
        List<AccountEntity> accounts = accountService.getAccountListByUser(user);

        StringBuilder responseMessage = new StringBuilder();
        responseMessage.append(String.format("There are %s accounts for user %s %s with oib: %s%n",
                accounts.size(), user.getFirstName(), user.getLastName(), user.getOib()));

        for (int i = 0; i< accounts.size(); i++) {
            responseMessage.append(String.format("%s. Account type: %s, Currency: %s, Balance: %s%n", i+1, accounts.get(i).getAccountType(), accounts.get(i).getCurrency(), accounts.get(i).getBalance()));
        }

        return ResponseEntity.ok(responseMessage.toString());
    }

}
