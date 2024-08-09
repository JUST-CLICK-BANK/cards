package com.example.card.domain.dto.response;

import com.example.card.domain.entity.Card;
import lombok.Builder;

@Builder
public record CardResponse (
        String cardPaymentDate,
        String cardName,
        String cardPassword,
        Long cardOneTimeLimit,
        Long cardMonthLimit



){
    public static CardResponse from(Card card) {
        return CardResponse.builder()
                .cardName(card.getCardName())
                .cardPaymentDate(card.getCardPaymentDate())
                .cardPassword(card.getCardPassword())
                .cardOneTimeLimit(card.getCardOneTimeLimit())
                .cardMonthLimit(card.getCardMonthLimit())
                .build();

    }

}
