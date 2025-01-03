package com.moja.banka.bankingsystem.dto;

import lombok.Getter;

@Getter
public class AuthResponseDTO {

    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
