package com.example.card.domain.entity;


import com.example.card.config.constants.CardTransportation;
import io.micrometer.core.instrument.binder.netty4.NettyAllocatorMetrics;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CARD_PRODUCTS")
public class CardProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARD_PRODUCT_ID")
    private Long cardProductId;

    @Column(name = "CARD_PRODUCT_NAME")
    private String cardProductName;

    @Column(name = "CARD_ANNUAL_FEE")
    private Long cardAnnualFee;


    @Column(name = "CARD_IMG")
    private String cardImg;

    @Column(name = "CARD_BENEFITS")
    private String cardBenefits;

//    @Column(name = "CARD_BRAND")
//    @Enumerated(EnumType.STRING)
//    private CardBrand cardBrand;

}
