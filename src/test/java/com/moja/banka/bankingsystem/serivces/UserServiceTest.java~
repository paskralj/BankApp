package com.moja.banka.bankingsystem.serivces;

import com.moja.banka.bankingsystem.entities.UserEntity;
import com.moja.banka.bankingsystem.exceptions.OibException;
import com.moja.banka.bankingsystem.repositories.UserRepository;
import com.moja.banka.bankingsystem.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    String oib = "12345678901";
    String rawPassword = "password123";
    String encodedPassword = "encodedPassword123";

    @Test
    void testRegisterUserWhenUserExists() {
        UserEntity user = new UserEntity();
        user.setOib(oib);
        when(userRepository.existsByOib(user.getOib())).thenReturn(true);
        assertThrows(OibException.class, () -> userService.registerUser(user));
    }

    @Test
    void testRegisterUserWhenUserDoesNotExist() {
        UserEntity user = new UserEntity();
        user.setOib(oib);
        user.setPassword(rawPassword);

        when(userRepository.existsByOib(any())).thenReturn(false);
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        userService.registerUser(user);

        verify(passwordEncoder).encode(rawPassword);
        verify(userRepository).save(user);
    }

}
