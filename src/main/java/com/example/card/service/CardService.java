package com.example.card.service;

import com.example.card.config.utils.jwt.TokenInfo;
import com.example.card.domain.dto.request.CardUpdateRequest;
import com.example.card.domain.dto.request.CardRequest;
import com.example.card.domain.dto.response.CardProductCardResponse;

import com.example.card.domain.entity.Card;
import java.util.List;
import java.util.UUID;

public interface CardService {
    void addCard(TokenInfo tokenInfo, CardRequest cardRequest);
    void updateCard(UUID userId,long cardId, CardUpdateRequest req);
    void deleteCard(UUID userId, String cardNumber);
    CardProductCardResponse getCardCardProductByCardID(long cardId);

    List<Card> getAllMyCard(UUID userId);

    Card getMyCard(Long cardId);
}
