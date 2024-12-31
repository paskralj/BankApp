package com.moja.banka.bankingsystem.services;

import com.moja.banka.bankingsystem.entities.AccountEntity;
import com.moja.banka.bankingsystem.entities.CardEntity;
import com.moja.banka.bankingsystem.entities.UserEntity;
import com.moja.banka.bankingsystem.enums.AccountType;
import com.moja.banka.bankingsystem.enums.CardStatus;
import com.moja.banka.bankingsystem.enums.CardType;
import com.moja.banka.bankingsystem.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final AccountService accountService;
    private final UserService userService;

    @Autowired
    public CardService(CardRepository cardRepository, AccountService accountService, UserService userService) {
        this.cardRepository = cardRepository;
        this.accountService = accountService;
        this.userService = userService;
    }

    public void generateCard(String oib, AccountType accountType, CardType cardType) {
        UserEntity user = userService.findUserByOib(oib);
        AccountEntity account = accountService.getAccountByUserAndAccountType(user, accountType);
        CardEntity card = new CardEntity();
        card.setUser(user);
        card.setAccount(account);
        card.setCardStatus(CardStatus.ACTIVE);
        card.setCardType(cardType);
        card.setExpirationDate(LocalDate.now().plusYears(5));
        card.setCardNumber(generateCardNumber());
        card.setCvv(generateCvv());
        cardRepository.save(card);
    }

    private String generateCardNumber() {
        int attempts = 0;
        String cardNumber;
        do {
            if(attempts > 10) {
                throw new RuntimeException("Could not generate card number");
            }
            StringBuilder cardNumberBuilder = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    cardNumberBuilder.append((int) (Math.random() * 10));
                }
                if (i < 3) {
                    cardNumberBuilder.append("-");
                }
            }
            cardNumber = cardNumberBuilder.toString();
            attempts++;
        } while (cardRepository.existsByCardNumber(cardNumber));

        return cardNumber;
    }

    private String generateCvv() {
        StringBuilder cvvBuilder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            cvvBuilder.append((int) (Math.random() * 10));
        }
        return cvvBuilder.toString();
    }
}
