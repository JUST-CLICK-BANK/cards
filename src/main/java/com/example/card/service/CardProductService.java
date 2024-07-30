package com.example.card.service;

import com.example.card.domain.dto.request.CardProductRequest;
import com.example.card.domain.dto.request.CardRequest;
import com.example.card.domain.dto.response.CardProductCardResponse;
import com.example.card.domain.dto.response.CardProductResponse;
import com.example.card.domain.entity.CardProduct;

import java.util.List;

public interface CardProductService {
    void addCardProduct(CardProductRequest req);
    void deleteCardProduct(long cardProductId);
    String getCardImgBycardID(long cardProductId);
    List<String> getAllCardImages();
//    List<CardProduct> getAllCardProducts()
    CardProductResponse getCardNameCardImgCardAnnualFeeCardBenefitByCardID(long cardProductId);

}
