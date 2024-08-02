package com.example.card.domain.dto.request;

import com.example.card.domain.entity.CreditHistory;
import java.util.Date;

public record CreditHistoryRequest(
    Long cardId,
    Long amount,
    String receiveName,
    String receiveAccount
) {

    public CreditHistory toEntity(Long amountSum, Integer category) {
        return CreditHistory.builder()
            .credit_pay_at(new Date())
            .credit_amount(amount)
            .credit_name(receiveName)
            .credit_receive(receiveAccount)
            .credit_amount_sum(amountSum)
            .credit_memo("")
            .build();
    }
}
