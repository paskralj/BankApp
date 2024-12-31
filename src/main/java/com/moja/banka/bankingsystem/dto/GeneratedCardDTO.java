package com.moja.banka.bankingsystem.dto;

import com.moja.banka.bankingsystem.enums.AccountType;
import com.moja.banka.bankingsystem.enums.CardType;
import lombok.Getter;

@Getter
public class GeneratedCardDTO {
    String oib;
    AccountType accountType;
    CardType cardType;
}
