package com.example.card.domain.dto.request;

public record CardUpdateRequest(
//        String cardNumber,
        String password,
        Long cardOneTimeLimit,
        Long cardMonthLimit,
        String cardName
){
}
