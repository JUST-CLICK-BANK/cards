package com.example.card.domain.dao;

import com.example.card.config.constants.CardTransportation;
import com.example.card.config.utils.jwt.TokenInfo;
import com.example.card.domain.dto.request.CardRequest;
import com.example.card.domain.entity.Card;

import java.util.Date;
import java.util.UUID;

public interface CardDao {
    void saveCard(CardRequest req, String cardNumber, UUID userId, String cardCVC, Date cardCreatedAt, TokenInfo tokenInfo);
    boolean compareCard(String generatedCardNumber);


    Card getCard(String cardNumber);
}
