package com.moja.banka.bankingsystem.entities;

import com.moja.banka.bankingsystem.enums.PaymentMethod;
import com.moja.banka.bankingsystem.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private TransactionEntity transaction;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private LocalDateTime paymentDate;

}
