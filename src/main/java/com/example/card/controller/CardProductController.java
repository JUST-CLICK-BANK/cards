package com.example.card.controller;

import com.example.card.domain.dto.request.CardProductRequest;
import com.example.card.domain.dto.response.CardProductResponse;
import com.example.card.domain.entity.CardProduct;
import com.example.card.service.CardProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/card-products")
@RequiredArgsConstructor

public class CardProductController {
    private final CardProductService cardProductService;
    private final ObjectMapper objectMapper;

    // TODO POST 요청으로 BODY 에 다음을 넣어줘야함
    // Get. 카드 상품 전체 목록 불러오기

    @QueryMapping(name = "getAllCardProduct")
    public List<CardProduct> getAllCardProduct() {
//  TODO { "query": "query { getAllCard { cardProductId cardImg cardProductName } }" }
        return cardProductService.getAllCardProduct();
    }

    // Get. 카드 상품 상세 정보 불러오기

    @QueryMapping(name = "getCardProduct")
    public CardProduct getCardProduct(@Argument(name = "cardProductId") Long cardProductId) {
//  TODO { "query": "query {getCardProduct(cardProductId:1){
//   cardProductId cardImg cardProductName cardAnnualFee cardBenefits
//   }}" }
        return cardProductService.getCardProductById(cardProductId);
    }

    @PostMapping
    public void createCardProduct(
            @RequestPart("cardProductRequest") String cardProductRequestJson,
            @RequestPart("cardImg") MultipartFile cardImg) throws Exception {
        CardProductRequest cardProductRequest = objectMapper.readValue(cardProductRequestJson, CardProductRequest.class);
        cardProductRequest = new CardProductRequest(
                cardProductRequest.cardProductName(),
                cardProductRequest.cardAnnualFee(),
                cardImg,
                cardProductRequest.cardBenefits()
        );
        cardProductService.addCardProduct(cardProductRequest);
    }


    @DeleteMapping("/{cardProductId}")
    public void deleteCardProduct(@PathVariable Long cardProductId) {
        cardProductService.deleteCardProduct(cardProductId);
    }




}
