package com.moja.banka.bankingsystem.entities;

import com.moja.banka.bankingsystem.enums.TransactionStatus;
import com.moja.banka.bankingsystem.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AccountEntity fromAccount;

    @ManyToOne
    private AccountEntity toAccount;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
}
