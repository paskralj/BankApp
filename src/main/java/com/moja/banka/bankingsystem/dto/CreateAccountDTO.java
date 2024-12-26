package com.moja.banka.bankingsystem.dto;

import com.moja.banka.bankingsystem.enums.AccountType;
import com.moja.banka.bankingsystem.enums.CurrencyType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountDTO {
    private String oib;
    private AccountType accountType;
    private CurrencyType currency;
}
