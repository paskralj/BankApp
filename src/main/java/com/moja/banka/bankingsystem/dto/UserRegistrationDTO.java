package com.moja.banka.bankingsystem.dto;

import com.moja.banka.bankingsystem.entities.UserEntity;
import com.moja.banka.bankingsystem.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserRegistrationDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
    private String password;

    public UserEntity toUserEntity(PasswordEncoder passwordEncoder) {
        UserEntity user = new UserEntity();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setDateOfBirth(dateOfBirth);
        user.setPassword(passwordEncoder.encode(this.password));

        user.setStatus(UserStatus.ACTIVE);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }
}
