package com.moja.banka.bankingsystem.services;

import com.moja.banka.bankingsystem.dto.CreateAccountDTO;
import com.moja.banka.bankingsystem.entities.AccountEntity;
import com.moja.banka.bankingsystem.entities.UserEntity;
import com.moja.banka.bankingsystem.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    public List<AccountEntity> getAccountListByUser(UserEntity user) {
        return accountRepository.findByUser(user);
    }

    public void createAccountForUser(CreateAccountDTO accountDto) {
        AccountEntity account = new AccountEntity();
        UserEntity user = userService.findUserByOib(accountDto.getOib());
        account.setUser(user);
        account.setBalance(BigDecimal.valueOf(0));
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        account.setAccountType(accountDto.getAccountType());
        account.setCurrency(accountDto.getCurrency());
        accountRepository.save(account);
    }
}
