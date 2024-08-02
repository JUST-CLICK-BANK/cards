package com.example.card.domain.dto.response;

import com.example.card.domain.entity.CreditHistory;
import java.util.Date;

public record CreditHistoryResponse(
    Long id,
    Date payAt,
    Long amount,
    String name,
    String receive,
    Long sum,
    String memo,
    String category
) {

    public static CreditHistoryResponse from(CreditHistory res) {
        return new CreditHistoryResponse(
            res.getCreditId(),
            res.getPayAt(),
            res.getAmount(),
            res.getReceiveName(),
            res.getReceiveAccount(),
            res.getAmountSum(),
            res.getMemo(),
            res.getCategoryId().getCategoryName()
        );
    }
}
