package com.moja.banka.bankingsystem.services;

import com.moja.banka.bankingsystem.entities.UserEntity;
import com.moja.banka.bankingsystem.exceptions.OibException;
import com.moja.banka.bankingsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserEntity user) {
        if (checkIfUserExists(user.getOib())) {
            throw new OibException("User with oib: " + user.getOib() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public UserEntity findUserByOib(String oib) {
        return userRepository.findByOib(oib).orElseThrow(() -> new OibException("User not found with oib: " + oib));
    }

    private boolean checkIfUserExists(String oib) {
        return userRepository.existsByOib(oib);
    }
}
