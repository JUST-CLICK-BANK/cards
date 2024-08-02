package com.example.card.domain.repository;

import com.example.card.domain.entity.Card;
import com.example.card.domain.entity.CreditMonth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditMonthRepository extends JpaRepository<CreditMonth, Card> {

}
