package com.moja.banka.bankingsystem.entities;

import com.moja.banka.bankingsystem.enums.LoanStatus;
import com.moja.banka.bankingsystem.enums.LoanType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;

    private BigDecimal amount;
    private BigDecimal interestRate;

    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    private BigDecimal monthlyInstallment;
}
