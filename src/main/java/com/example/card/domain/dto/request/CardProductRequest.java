package com.example.card.domain.dto.request;

import com.example.card.domain.entity.CardProduct;
import org.springframework.web.multipart.MultipartFile;

public record CardProductRequest (
        String cardProductName,
        String cardAnnualFee,
        MultipartFile cardImg,
        String cardBenefits
){
   public CardProduct toEntity() {
       return CardProduct.builder()
               .cardProductName(cardProductName)
               .cardAnnualFee(cardAnnualFee)
               .cardImg(cardImg != null ? cardImg.getOriginalFilename() : null)
               .cardBenefits(cardBenefits)
               .build();
   }
}
