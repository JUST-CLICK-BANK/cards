package com.example.card.service;

import com.example.card.config.utils.jwt.TokenInfo;
import com.example.card.domain.dto.request.*;
import com.example.card.domain.dto.response.CardProductCardResponse;

import com.example.card.domain.dto.response.CardResponse;
import com.example.card.domain.entity.Card;
import java.util.List;
import java.util.UUID;

public interface CardService {
    void addCard(TokenInfo tokenInfo, CardRequest cardRequest);
    void updateCard(UUID userId,long cardId, CardUpdateRequest req);
    void deleteCard(UUID userId, String cardNumber);
    CardProductCardResponse getCardCardProductByCardID(long cardId);
//    CardResponse getCard(long cardId);
    List<Card> getAllMyCard(TokenInfo tokenInfo);
//    String getCardByCardID(long cardId);
    Card getMyCard(Long cardId);
    void updateCardPassword(UUID userId,long cardId, CardPasswordRequest req);
    void updateCardName(UUID userId,long cardId, CardNameRequest req);
    void updateCardOneTimeLimit(UUID userId,long cardId, CardOneTimeLimitRequest req);
    void updateCardMonthLimit(UUID userId,long cardId, CardMonthLimitRequest req);
    void updateCardPaymentDate(UUID userId,long cardId, CardPatmentDateRequest req);
    Boolean getAbleMyCard(Long cardId);
    void deleteCardFromAccount(UUID userId ,String account);
}
