package com.example.card.domain.dto.response;

import com.example.card.domain.entity.CardProduct;
import lombok.Builder;

@Builder
public record CardProductResponse(
        String cardProductName,
        Long cardAnnualFee,
        String cardImg,
        String cardBenefits

){
    public static CardProductResponse from(CardProduct cardProduct) {
        return CardProductResponse.builder()
                .cardProductName(cardProduct.getCardProductName())
                .cardAnnualFee(cardProduct.getCardAnnualFee())
                .cardImg(cardProduct.getCardImg())
                .cardBenefits(cardProduct.getCardBenefits())
                .build();
    }
}
