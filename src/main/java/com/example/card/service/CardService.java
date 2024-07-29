package com.example.card.service;

import com.example.card.config.utils.jwt.TokenInfo;
import com.example.card.domain.dto.request.CardUpdateRequest;
import com.example.card.domain.dto.request.CardRequest;
import com.example.card.domain.dto.response.CardProductCardResponse;

import java.util.UUID;

public interface CardService {
    void addCard(TokenInfo tokenInfo, CardRequest cardRequest);
    void updateCard(UUID userId,long cardId, CardUpdateRequest req);
    void deleteCard(UUID userId, String cardNumber);
    CardProductCardResponse getCardCardProductByCardID(long cardId);

}
