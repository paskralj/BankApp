package com.moja.banka.bankingsystem.entities;

import com.moja.banka.bankingsystem.enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Entity
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private BigDecimal balance;
    private String currency;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}