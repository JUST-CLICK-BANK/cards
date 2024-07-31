package com.example.card.domain.entity;

import com.example.card.config.constants.CardCheck;
import com.example.card.config.constants.CardTransportation;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CARDS")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CARD_ID")
    private long cardId;

    @Setter
    @Column(name = "CARD_NAME")
    private String cardName;

    @Column(name = "CARD_NUMBER")
    private String cardNumber;

    @Column(name = "CARD_ONE_TIME_LIMIT")
    private Long cardOneTimeLimit;

    @Column(name = "ACCOUNT")
    private String account;

    @Column(name = "CARD_CVC")
    private String cardCVC;

    @Column(name = "CARD_CHECK")
    @Enumerated(EnumType.STRING)
    private CardCheck cardCheck;

    @Column(name ="USER_ID")
    private UUID userId;

    @Column(name = "CARD_MONTH_LIMIT")
    private Long cardMonthLimit;

    @Column(name = "CARD_ANNUAl_FEE")
    private String cardAnnualFee;

//    @Column(name = "CARD_VALIDITY")
//    private Date cardValidity;

    @Column(name = "CARD_PASSWORD")
    @Setter
    private String cardPassword;

    @Column(name = "CARD_SALT")
    private String cardSalt;

    @Column(name = "CARD_DISABLE")
    @Setter
    private Boolean cardDisable;

    @Column(name = "CARD_CREATED_AT")
    private Date cardCreatedAt;

    @ManyToOne
    @JoinColumn(name = "CARD_PRODUCT_ID")
    private CardProduct cardProduct;

    @Column(name = "CARD_TRANSPORTATION")
    @Enumerated(EnumType.STRING)
    private CardTransportation cardTransportation;


    public void updateCard(String cardPassword,Long cardOneTimeLimit,Long cardMonthLimit,String cardName){
        this.cardPassword = cardPassword;
        this.cardOneTimeLimit = cardOneTimeLimit;
        this.cardMonthLimit = cardMonthLimit;
        this.cardName = cardName;
//        this.cardNumber = cardNumber;
    }

}
