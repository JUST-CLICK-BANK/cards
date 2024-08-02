package com.example.card.domain.repository;

import com.example.card.domain.entity.CreditHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditHistoryRepository extends JpaRepository<CreditHistory, Long> {

}
