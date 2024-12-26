package com.moja.banka.bankingsystem.entities;

import com.moja.banka.bankingsystem.enums.AccountType;
import com.moja.banka.bankingsystem.enums.CurrencyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @NotNull
    private BigDecimal balance;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
