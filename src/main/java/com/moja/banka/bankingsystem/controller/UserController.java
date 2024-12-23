package com.moja.banka.bankingsystem.controller;

import com.moja.banka.bankingsystem.dto.AuthResponseDTO;
import com.moja.banka.bankingsystem.dto.LoginDTO;
import com.moja.banka.bankingsystem.dto.UserRegistrationDTO;
import com.moja.banka.bankingsystem.entities.UserEntity;
import com.moja.banka.bankingsystem.security.JwtGenerator;
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

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtGenerator jwtGenerator) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
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
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(String.format("There is a user %s %s with oib: %s ", user.getFirstName(), user.getLastName(), user.getOib()));
    }

}
