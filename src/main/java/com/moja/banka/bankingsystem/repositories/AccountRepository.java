package com.moja.banka.bankingsystem.repositories;

import com.moja.banka.bankingsystem.entities.AccountEntity;
import com.moja.banka.bankingsystem.entities.UserEntity;
import com.moja.banka.bankingsystem.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByUser(UserEntity user);
    boolean existsByUserAndAccountType(UserEntity user, AccountType accountType);
    Optional<AccountEntity> findByUserAndAccountType(UserEntity user, AccountType accountType);
}
