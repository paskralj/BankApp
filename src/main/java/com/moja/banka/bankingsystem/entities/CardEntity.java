package com.moja.banka.bankingsystem.entities;

import com.moja.banka.bankingsystem.enums.CardStatus;
import com.moja.banka.bankingsystem.enums.CardType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private AccountEntity account;

    private String cardNumber;
    private LocalDate expirationDate;
    private String cvv;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;
}
