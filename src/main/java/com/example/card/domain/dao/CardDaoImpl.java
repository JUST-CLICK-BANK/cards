package com.example.card.domain.dao;

import com.example.card.config.utils.jwt.TokenInfo;
import com.example.card.domain.dto.request.CardRequest;
import com.example.card.domain.entity.Card;
import com.example.card.domain.entity.CardProduct;
import com.example.card.domain.repository.CardProductRepository;
import com.example.card.domain.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CardDaoImpl implements CardDao{
    private final CardRepository cardRepository;
    private final CardProductRepository cardProductRepository;

    @Override
    public void saveCard(CardRequest req, String cardNumber, UUID userId, String cardCVC, Date cardCreatedAt, TokenInfo tokenInfo) {
        String cardName = tokenInfo.name();
        CardProduct cardProduct = cardProductRepository.findById(req.cardProductId())
                        .orElseThrow(()-> new RuntimeException("카드 상춤 조회 안됨"));
        cardRepository.save(
                req.toEntity(
                        cardNumber,
                        true,
                        1000000L,
                        1000000L,
                        userId,
                        cardCVC,
                        "100000",
                        cardCreatedAt,
                        cardName+"의 카드",
                        cardProduct



                )
        );
    }
    @Override
    public boolean compareCard(String generatedCardNumber) {
        return cardRepository.findByCardNumber(generatedCardNumber).isPresent();
    }

    @Override
    public Card getCard(String cardNumber){
        return cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(IllegalArgumentException::new);
    }
    }











