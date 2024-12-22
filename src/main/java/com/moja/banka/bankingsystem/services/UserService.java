package com.moja.banka.bankingsystem.services;

import com.moja.banka.bankingsystem.entities.UserEntity;
import com.moja.banka.bankingsystem.exceptions.OibNotFoundException;
import com.moja.banka.bankingsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserEntity user) {
        userRepository.save(user);
    }

    public UserEntity findUserByOib(String oib) {
        return userRepository.findByOib(oib).orElseThrow(() -> new OibNotFoundException("User not found with oib: " + oib));
    }
}
