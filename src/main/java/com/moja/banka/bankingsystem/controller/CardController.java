package com.moja.banka.bankingsystem.controller;

import com.moja.banka.bankingsystem.dto.GeneratedCardDTO;
import com.moja.banka.bankingsystem.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/generate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> generateCard(@RequestBody GeneratedCardDTO generatedCardDTO) {
        cardService.generateCard(generatedCardDTO.getOib(), generatedCardDTO.getAccountType(), generatedCardDTO.getCardType());
        return new ResponseEntity<>("Card generated successfully!" , HttpStatus.OK);
    }
}
