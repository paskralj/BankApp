package com.moja.banka.bankingsystem.controller;

import com.moja.banka.bankingsystem.dto.UserRegistrationDTO;
import com.moja.banka.bankingsystem.entities.UserEntity;
import com.moja.banka.bankingsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok("User registered successfully, memorize your user id: " + user.getOib());
    }

    @GetMapping("/oib/{oib}")
    public ResponseEntity<String> getUserByOib(@PathVariable String oib) {
        UserEntity user = userService.findUserByOib(oib);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("There is a user with that oib: " + oib);
    }

    //TODO: Modificiati getUserByOib, tako da provjerimo jeli postoji i to samo admin moze provjeriti a ne user!

}
