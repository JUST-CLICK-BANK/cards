package com.example.card.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @OneToOne
    @JoinColumn(name = "CARD_ID", nullable = false)
    private Card cardId;

    @Column(name = "CM_AMOUNT", nullable = false)
    private Long credit_month_amount;
}
