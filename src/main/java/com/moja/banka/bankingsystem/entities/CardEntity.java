package com.moja.banka.bankingsystem.entities;

import com.moja.banka.bankingsystem.enums.CardStatus;
import com.moja.banka.bankingsystem.enums.CardType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotBlank
    private UserEntity user;

    @ManyToOne
    @NotBlank
    private AccountEntity account;

    @NotBlank
    @Column(unique = true)
    private String cardNumber;

    @NotBlank
    private LocalDate expirationDate;

    @NotBlank
    private String cvv;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CardType cardType;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CardStatus cardStatus;
}
