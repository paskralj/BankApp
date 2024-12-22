package com.moja.banka.bankingsystem.repositories;

import com.moja.banka.bankingsystem.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
