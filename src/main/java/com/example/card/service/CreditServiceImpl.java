package com.example.card.service;

import com.example.card.domain.dto.request.CreditHistoryRequest;
import com.example.card.domain.dto.response.CreditHistoryResponse;
import com.example.card.domain.entity.Card;
import com.example.card.domain.entity.CreditHistory;
import com.example.card.domain.entity.CreditMonth;
import com.example.card.domain.repository.CreditHistoryRepository;
import com.example.card.domain.repository.CreditMonthRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final CreditHistoryRepository creditHistoryRepository;
    private final CreditMonthRepository creditMonthRepository;

    @Override
    public List<CreditHistoryResponse> getCreditHistory(Long card) {
        List<CreditHistory> histories = creditHistoryRepository.findAllByCardId(card);
        return histories.stream().map(CreditHistoryResponse::from).toList();
    }

    @Override
    public Long getMonthAmount(Long card) {
        CreditMonth creditMonth = creditMonthRepository.findById(
            Card.builder().cardId(card).build()).orElseThrow(IllegalArgumentException::new);
        return creditMonth.getCredit_month_amount();
    }

    @Override
    @Transactional
    public void createCreditHistory(CreditHistoryRequest req) {
        Long totalAmount = getMonthAmount(req.cardId()) + req.amount();
        Integer category = 0; // todo 카테고리 분류 알고리즘 필요
        CreditHistory creditHistory = req.toEntity(totalAmount, category);
        creditHistoryRepository.save(creditHistory);
    }
}
