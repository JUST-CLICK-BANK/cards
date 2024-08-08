package com.example.card.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "CREDIT_MONTH")
public class CreditMonth {

    @Id
    private Long creditMonthId;

    @OneToOne
    @MapsId(value = "creditMonthId")
    @JoinColumn(name = "CARD_ID")
    private Card cardId;

    @Column(name = "CM_AMOUNT", nullable = false)
    private Long creditMonthAmount;

    public Long addAmount(Long amount) {
        creditMonthAmount += amount;
        return creditMonthAmount;
    }

    public void resetAmount() {
        creditMonthAmount = 0L;
    }
}
