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
    @CrossOrigin(origins = "http://localhost:8080")
    @QueryMapping(name = "getAllCardProduct")
    public List<CardProduct> getAllCardProduct() {
//  TODO { "query": "query { getAllCard { cardProductId cardImg cardProductName } }" }
        return cardProductService.getAllCardProduct();
    }

    // Get. 카드 상품 상세 정보 불러오기
    @CrossOrigin(origins = "http://localhost:8080")
    @QueryMapping(name = "getCardProduct")
    public CardProduct getCardProduct(@Argument Long cardProductId) {
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
//    @PostMapping
//    public void createCardProduct(@RequestBody CardProductRequest cardProductRequest) {
//        cardProductService.addCardProduct(cardProductRequest);
//    }

    @DeleteMapping("/{cardProductId}")
    public void deleteCardProduct(@PathVariable Long cardProductId) {
        cardProductService.deleteCardProduct(cardProductId);
    }

    // TODO CardController 에서 getAllMyCard()로 대체
    //카드 id로 카드 이미지 불러옴(CardComplete페이지)
    // @GetMapping("/image/{cardProductId}")
    // public String getCardImgBycardID(@PathVariable("cardProductId") long cardProductId){
    //     return cardProductService.getCardImgBycardID(cardProductId);
    // }

    // TODO getAllCardProduct()로 대체
    //카드 이미지 전체 불러오기(AddCardList페이지)
    // @GetMapping("/images")
    // public List<String> getAllCardImages() {
    //     return cardProductService.getAllCardImages();
    // }

    // TODO getCardProduct(cardProductId)로 대체
    //카드상품 아이디로 카드상품이름,연회비,카드 이미지,카드 적립률(cardInformation 페이지)
    // @GetMapping("/{cardProductId}")
    // public Optional<CardProductResponse> getCardBycardID(@PathVariable("cardProductId") long cardProductId) {
    //     CardProductResponse cardProductResponse = cardProductService.getCardNameCardImgCardAnnualFeeCardBenefitByCardID(cardProductId);
    //     return Optional.of(cardProductResponse);
    // }


}
