package com.example.card.service;

import com.example.card.domain.dto.request.CreditHistoryRequest;
import com.example.card.domain.dto.response.CreditHistoryResponse;
import java.util.List;

public interface CreditService {
    List<CreditHistoryResponse> getCreditHistory(Long card);
    Long getMonthAmount(Long card);
    void createCreditHistory(CreditHistoryRequest req);
}
