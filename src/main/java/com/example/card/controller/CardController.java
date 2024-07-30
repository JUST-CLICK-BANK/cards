package com.example.card.controller;

import com.example.card.config.utils.jwt.JwtUtils;
import com.example.card.config.utils.jwt.TokenInfo;
import com.example.card.domain.dto.request.CardRequest;
import com.example.card.domain.dto.request.CardUpdateRequest;
import com.example.card.domain.dto.response.CardProductCardResponse;
import com.example.card.domain.entity.Card;
import com.example.card.service.CardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    private final JwtUtils jwtUtils;

    @QueryMapping(name = "getAllMyCard")
    public List<Card> getAllMyCard(@Argument UUID userId) {
        return cardService.getAllMyCard(userId);
    }

    @QueryMapping(name = "getMyCard")
    public Card getMyCard(@Argument Long cardId) {
        return cardService.getMyCard(cardId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void  createCard(@RequestHeader("Authorization") String bearerToken,
                           @RequestBody CardRequest cardRequest) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtUtils.parseUserToken(token);
        cardService.addCard(tokenInfo, cardRequest);

    }
    @PutMapping("/{cardId}")
    public  void updateCardPassword(@PathVariable long cardId,
                                    @RequestHeader("Authorization") String bearerToken,
                                    @RequestBody CardUpdateRequest req) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtUtils.parseUserToken(token);
        cardService.updateCard(UUID.fromString(tokenInfo.id()),cardId,req);
    }
    @DeleteMapping()
    public void deleteCard(@RequestHeader("Authorization") String bearerToken,
                           @RequestParam ("cardNumber") String cardNumber) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtUtils.parseUserToken(token);
        cardService.deleteCard(UUID.fromString(tokenInfo.id()),cardNumber);

    }

    // TODO getMyCard()로 대체
    //카드 아이디로 카드이름,카드상품 이미지,카드번호,계좌 번호(MyCardInformation페이지)
    // @GetMapping("/{cardId}")
    // public Optional<CardProductCardResponse> getCardProduct(@PathVariable long cardId) {
    //         CardProductCardResponse cardProductCardResponse = cardService.getCardCardProductByCardID(cardId);
    //         return Optional.ofNullable(cardProductCardResponse);
    // }

}
