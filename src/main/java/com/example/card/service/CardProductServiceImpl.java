package com.example.card.service;

import com.example.card.domain.dto.response.CardProductResponse;
import com.example.card.domain.entity.CardProduct;
import com.example.card.domain.repository.CardProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardProductServiceImpl implements  CardProductService{
    private final CardProductRepository cardProductRepository;

//    @Override
//    public String getCardImgBycardID(long cardProductId){
//        return cardProductRepository.findCardImgByCardProductId(cardProductId)
//                .
//
//    }
    @Override
    public String getCardImgBycardID(long cardProductId) {
        return cardProductRepository.findCardImgByCardProductId(cardProductId)
                .describeConstable().orElseThrow(() -> new RuntimeException("Card product not found"));

    }

    @Override
    public List<String> getAllCardImages() {
        return cardProductRepository.findAllCardImages();
    }

    @Override
    public CardProductResponse getCardNameCardImgCardAnnualFeeCardBenefitByCardID(long cardProductId) {
        CardProduct cardProduct = cardProductRepository.findCardProductByCardProductId(cardProductId)
                .orElseThrow(() -> new RuntimeException("Card product not found"));
        return CardProductResponse.from(cardProduct);
    }

}
