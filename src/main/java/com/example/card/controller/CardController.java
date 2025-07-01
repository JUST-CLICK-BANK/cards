package com.example.card.controller;

import com.example.card.config.utils.jwt.JwtUtils;
import com.example.card.config.utils.jwt.TokenInfo;
import com.example.card.domain.dto.request.*;
import com.example.card.domain.dto.response.CardProductCardResponse;
import com.example.card.domain.dto.response.CardResponse;
import com.example.card.domain.entity.Card;
import com.example.card.service.CardService;
import graphql.GraphQLContext;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    private final JwtUtils jwtUtils;

    @QueryMapping(name = "getAllMyCard")
    public List<Card> getAllMyCard(GraphQLContext context) {
        String bearerToken = context.get("Authorization");
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtUtils.parseUserToken(token);
        return cardService.getAllMyCard(tokenInfo);
    }


    @QueryMapping(name = "getMyCard")
    public Card getMyCard(@Argument(name = "cardId") Long cardId) {
        return cardService.getMyCard(cardId);
    }

    @GetMapping("/pay/{cardId}")
    public Boolean getAbleMycard(@PathVariable Long cardId) {
        return cardService.getAbleMyCard(cardId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createCard(@RequestHeader("Authorization") String bearerToken,
                           @RequestBody CardRequest cardRequest) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtUtils.parseUserToken(token);
        cardService.addCard(tokenInfo, cardRequest);
    }

    @PutMapping("/{cardId}")
    public void updateCard(@PathVariable long cardId,
                           @RequestHeader("Authorization") String bearerToken,
                           @RequestBody CardUpdateRequest req) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtUtils.parseUserToken(token);
        cardService.updateCard(UUID.fromString(tokenInfo.id()), cardId, req);
    }

    @PutMapping("/password/{cardId}")
    public void updateCardPassword(@PathVariable long cardId,
                                   @RequestHeader("Authorization") String bearerToken,
                                   @RequestBody CardPasswordRequest req) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtUtils.parseUserToken(token);
        cardService.updateCardPassword(UUID.fromString(tokenInfo.id()), cardId, req);
    }

    @PutMapping("/card-name/{cardId}")
    public void updateCardName(@PathVariable long cardId,
                               @RequestHeader("Authorization") String bearerToken,
                               @RequestBody CardNameRequest req) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtUtils.parseUserToken(token);
        cardService.updateCardName(UUID.fromString(tokenInfo.id()), cardId, req);
    }

    @PutMapping("/day-limit/{cardId}")
    public void updateCardOneTimeLimit(@PathVariable long cardId,
                                       @RequestHeader("Authorization") String bearerToken,
                                       @RequestBody CardOneTimeLimitRequest req) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtUtils.parseUserToken(token);
        cardService.updateCardOneTimeLimit(UUID.fromString(tokenInfo.id()), cardId, req);
    }

    @PutMapping("/month-limit/{cardId}")
    public void updateCardMonthLimit(@PathVariable long cardId,
                                     @RequestHeader("Authorization") String bearerToken,
                                     @RequestBody CardMonthLimitRequest req) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtUtils.parseUserToken(token);
        cardService.updateCardMonthLimit(UUID.fromString(tokenInfo.id()), cardId, req);
    }

    @PutMapping("/payment-date/{cardId}")
    public void updateCardPaymentDate(@PathVariable long cardId,
                                      @RequestHeader("Authorization") String bearerToken,
                                      @RequestBody CardPatmentDateRequest req) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtUtils.parseUserToken(token);
        cardService.updateCardPaymentDate(UUID.fromString(tokenInfo.id()), cardId, req);
    }


    @DeleteMapping()
    public void deleteCard(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam("cardNumber") String cardNumber
    ) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtUtils.parseUserToken(token);
        cardService.deleteCard(UUID.fromString(tokenInfo.id()), cardNumber);

    }
    @DeleteMapping("/account")
    public void deleteAccountCard(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam("account") String account
    ) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtUtils.parseUserToken(token);
        cardService.deleteCardFromAccount(UUID.fromString(tokenInfo.id()), account);

    }
}

