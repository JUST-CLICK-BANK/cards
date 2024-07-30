package com.example.card.domain.dto.request;

import com.example.card.domain.entity.CardProduct;

public record CardProductRequest (
        String cardProductName,
        Long cardAnnualFee,
        String cardImg,
        String cardBenefits
){
   public CardProduct toEntity() {
       return CardProduct.builder()
               .cardProductName(cardProductName)
               .cardAnnualFee(cardAnnualFee)
               .cardImg(cardImg)
               .cardBenefits(cardBenefits)
               .build();
   }
}
