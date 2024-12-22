package com.moja.banka.bankingsystem.repositories;

import com.moja.banka.bankingsystem.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
}
