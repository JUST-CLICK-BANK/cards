package com.example.card.service;

import com.example.card.domain.dto.request.CreditHistoryRequest;
import com.example.card.domain.dto.response.CreditHistoryResponse;
import com.example.card.domain.entity.Card;
import com.example.card.domain.entity.CreditHistory;
import com.example.card.domain.entity.CreditMonth;
import com.example.card.domain.repository.CreditHistoryRepository;
import com.example.card.domain.repository.CreditMonthRepository;
import com.example.card.global.domain.entity.Category;
import com.example.card.global.domain.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final CategoryRepository categoryRepository;
    private final CreditHistoryRepository creditHistoryRepository;
    private final CreditMonthRepository creditMonthRepository;

    @Override
    public List<CreditHistoryResponse> getCreditHistory(Long card) {
        List<CreditHistory> histories =
            creditHistoryRepository.findAllByCardId(Card.builder().cardId(card).build());
        return histories.stream().map(CreditHistoryResponse::from).toList();
    }

    @Override
    public Long getMonthAmount(Long card) {
        CreditMonth creditMonth = creditMonthRepository.findById(card)
            .orElseThrow(RuntimeException::new);
        return creditMonth.getCreditMonthAmount();
    }

    @Override
    @Transactional
    public void createCreditHistory(CreditHistoryRequest req) {
        CreditMonth creditMonth = creditMonthRepository.findById(req.cardId())
            .orElseThrow(RuntimeException::new);
        Long totalAmount = creditMonth.addAmount(req.amount());
        Category category = Category.builder().categoryId(1).build(); // todo 카테고리 분류 알고리즘 필요
        Card card = Card.builder().cardId(req.cardId()).build();
        CreditHistory creditHistory = req.toEntity(totalAmount, card, category);
        creditHistoryRepository.save(creditHistory);
    }
}
