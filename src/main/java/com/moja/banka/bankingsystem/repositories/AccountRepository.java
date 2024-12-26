package com.moja.banka.bankingsystem.repositories;

import com.moja.banka.bankingsystem.entities.AccountEntity;
import com.moja.banka.bankingsystem.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByUser(UserEntity user);
}
