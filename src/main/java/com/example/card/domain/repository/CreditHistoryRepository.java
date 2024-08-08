package com.example.card.domain.repository;

import com.example.card.domain.entity.Card;
import com.example.card.domain.entity.CreditHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditHistoryRepository extends JpaRepository<CreditHistory, Long> {

    List<CreditHistory> findAllByCardId(Card card);
}
