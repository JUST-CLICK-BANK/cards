package com.example.card.service;

import com.example.card.domain.dto.request.CreditHistoryRequest;
import com.example.card.domain.dto.response.CreditHistoryResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService{

    @Override
    public List<CreditHistoryResponse> getCreditHistory(Long card) {
        return List.of();
    }

    @Override
    public Long getMonthAmount(Long card) {
        return null;
    }

    @Override
    public void createCreditHistory(CreditHistoryRequest req) {

    }
}
