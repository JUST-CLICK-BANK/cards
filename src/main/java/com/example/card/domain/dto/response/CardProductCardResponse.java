package com.example.card.domain.dto.response;

import com.example.card.domain.entity.Card;
import com.example.card.domain.entity.CardProduct;
import lombok.Builder;

@Builder
public record CardProductCardResponse(
        String cardName,
        String cardImg,
        String cardNumber,
        String account
) {
    public static CardProductCardResponse from(CardProduct cardProduct, Card card) {
        return  CardProductCardResponse.builder()
                .cardName(card.getCardName())
                .cardImg(cardProduct.getCardImg())
                .cardNumber(card.getCardNumber())
                .account(card.getAccount())
                .build();
    }
}
