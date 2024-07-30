package com.example.card.controller;

import com.example.card.domain.dto.request.CardProductRequest;
import com.example.card.domain.dto.response.CardProductResponse;
import com.example.card.service.CardProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/card-product")
@RequiredArgsConstructor
public class CardProductController {
    private final CardProductService cardProductService;

    @PostMapping
    public void createCardProduct(@RequestBody CardProductRequest cardProductRequest) {
        cardProductService.addCardProduct(cardProductRequest);
    }

    @DeleteMapping("/{cardProductId}")
    public void deleteCardProduct(@PathVariable Long cardProductId) {
        cardProductService.deleteCardProduct(cardProductId);
    }
    //카드 id로 카드 이미지 불러옴(CardComplete페이지)
    @GetMapping("/image/{cardProductId}")
    public String getCardImgBycardID(@PathVariable("cardProductId") long cardProductId){
        return cardProductService.getCardImgBycardID(cardProductId);

    }
    //카드 이미지 전체 불러오기(AddCardList페이지)
    @GetMapping("/images")
    public List<String> getAllCardImages() {
        return cardProductService.getAllCardImages();
    }
    //카드상품 아이디로 카드상품이름,연회비,카드 이미지,카드 적립률(cardInformation페이지)
    @GetMapping("/{cardProductId}")
    public Optional<CardProductResponse> getCardBycardID(@PathVariable("cardProductId") long cardProductId) {
        CardProductResponse cardProductResponse = cardProductService.getCardNameCardImgCardAnnualFeeCardBenefitByCardID(cardProductId);
        return Optional.of(cardProductResponse);
    }


}
