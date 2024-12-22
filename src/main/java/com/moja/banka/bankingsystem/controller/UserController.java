package com.moja.banka.bankingsystem.controller;

import com.moja.banka.bankingsystem.dto.UserRegistrationDTO;
import com.moja.banka.bankingsystem.entities.UserEntity;
import com.moja.banka.bankingsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserEntity user = userRegistrationDTO.toUserEntity(passwordEncoder);
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully, memorize your user id: " + user.getId());
    }


}
