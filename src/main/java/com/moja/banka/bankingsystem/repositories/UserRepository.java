package com.moja.banka.bankingsystem.repositories;

import com.moja.banka.bankingsystem.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByOib(String oib);
    Boolean existsByOib(String oib);
}
