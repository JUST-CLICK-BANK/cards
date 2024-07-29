package com.example.card.domain.dto.request;

import com.example.card.config.constants.CardCheck;
import com.example.card.config.constants.CardTransportation;
import com.example.card.domain.entity.Card;
import com.example.card.domain.entity.CardProduct;

import java.util.Date;
import java.util.UUID;

public record CardRequest (
        String cardPassword,
        CardCheck cardCheck,
        String account,
        CardTransportation cardTransportation,
        long cardProductId
) {
    public Card toEntity(
            String cardNumber,
            Boolean cardDisable,
            Long cardOneTimeLimit,
            Long cardMonthLimit,
            UUID userId,
            String cardCVC,
            String cardAnnualFee,
            Date cardCreatedAt,
            String cardName,
            CardProduct cardproduct


            ) {
        return Card.builder()
                .account(account)
                .cardPassword(cardPassword)
                .cardCheck(cardCheck)
                .cardProduct(cardproduct)
                .userId(userId)
                .cardOneTimeLimit(cardOneTimeLimit)
                .cardMonthLimit(cardMonthLimit)
                .cardNumber(cardNumber)
                .cardCVC(cardCVC)
                .cardAnnualFee(cardAnnualFee)
                .cardDisable(cardDisable)
                .cardCreatedAt(cardCreatedAt)
                .cardName(cardName)
                .cardTransportation(cardTransportation)
                .build();
    }
}
