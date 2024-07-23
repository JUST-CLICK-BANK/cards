package com.example.card.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CARDS")
public class Card {
    @Id
    @Column(name = "CARD_ID")
    private long cardId;

    @Column(name = "CARD_ONE_TIME_LIMIT")
    private String cardOneTimeLimit;

    @Column(name = "ACCOUNT")
    private String account;

    @Column(name = "CARD_NUMBER")
    private String cardNumber;

    @Column(name = "CARD_CVC")
    private String cardCVC;

    @Column(name = "CARD_CHECK")
    private Enum cardCheck;

    @Column(name ="USER_ID")
    private UUID userId;

    @Column(name = "CARD_MONTH_LIMIT")
    private String cardMonthLimit;

    @Column(name = "CARD_ANNUAl_FEE")
    private String cardAnnualFee;

}
