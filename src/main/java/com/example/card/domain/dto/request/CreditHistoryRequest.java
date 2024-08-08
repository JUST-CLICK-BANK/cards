package com.example.card.domain.dto.request;

import com.example.card.domain.entity.Card;
import com.example.card.domain.entity.CreditHistory;
import com.example.card.global.domain.entity.Category;
import java.util.Date;

public record CreditHistoryRequest(
    Long cardId,
    Long amount,
    String receiveName,
    String receiveAccount
) {

    public CreditHistory toEntity(Long amountSum, Card card, Category category) {
        return CreditHistory.builder()
            .payAt(new Date())
            .amount(amount)
            .receiveName(receiveName)
            .receiveAccount(receiveAccount)
            .amountSum(amountSum)
            .memo("")
            .cardId(card)
            .categoryId(category)
            .build();
    }
}
