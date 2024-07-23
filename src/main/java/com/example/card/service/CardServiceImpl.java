package com.example.card.service;

import com.example.card.domain.entity.Card;
import com.example.card.domain.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl {
    private final CardRepository cardRepository;

    @Override
    public void addCard(Card card) {

    }

}
