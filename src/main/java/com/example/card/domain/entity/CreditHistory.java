package com.example.card.domain.entity;

import com.example.card.global.domain.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "CREDIT_HISTORIES")
public class CreditHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CH_ID")
    private Long credit_id;

    @Column(name = "CH_PAY_AT", nullable = false)
    private Date credit_pay_at;

    @Column(name = "CH_AMOUNT", nullable = false)
    private Long credit_amount;

    @Column(name = "CH_RECEIVE_NAME", nullable = false)
    private String credit_name;

    @Column(name = "CH_RECEIVE_ACCOUNT", nullable = false)
    private String credit_receive;

    @Column(name = "CH_AMOUNT_SUM", nullable = false)
    private Long credit_amount_sum;

    @Column(name = "CH_MEMO", nullable = false)
    private String credit_memo;

    @ManyToOne
    @Setter
    @JoinColumn(name = "CARD_ID", nullable = false)
    private Card cardId;

    @ManyToOne
    @Setter
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category categoryId;
}
