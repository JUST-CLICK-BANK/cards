package com.example.card.service;

import com.example.card.domain.dto.response.CardProductCardResponse;
import com.example.card.domain.dto.response.CardProductResponse;

import java.util.List;

public interface CardProductService {
    String getCardImgBycardID(long cardProductId);
    List<String> getAllCardImages();
//    List<CardProduct> getAllCardProducts()
    CardProductResponse getCardNameCardImgCardAnnualFeeCardBenefitByCardID(long cardProductId);

}
