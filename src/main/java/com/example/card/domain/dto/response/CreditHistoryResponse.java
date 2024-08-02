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
    String memo
) {

    public static CreditHistoryResponse from(CreditHistory res) {
        return new CreditHistoryResponse(
            res.getCredit_id(),
            res.getCredit_pay_at(),
            res.getCredit_amount(),
            res.getCredit_name(),
            res.getCredit_receive(),
            res.getCredit_amount_sum(),
            res.getCredit_memo()
        );
    }
}
