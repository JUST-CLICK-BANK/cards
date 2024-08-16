package com.example.card.domain.dto.request;

public record CardUpdateRequest(

        String password,
        Long cardOneTimeLimit,
        Long cardMonthLimit,
        String cardName,
        String cardPaymentDate
){
}
