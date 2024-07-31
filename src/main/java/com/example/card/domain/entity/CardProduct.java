package com.example.card.domain.entity;


import com.example.card.config.constants.CardTransportation;
import io.micrometer.core.instrument.binder.netty4.NettyAllocatorMetrics;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CARD_PRODUCTS")
public class CardProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CARD_PRODUCT_ID")
    private long cardProductId;

    @Column(name = "CARD_PRODUCT_NAME")
    private String cardProductName;

    @Column(name = "CARD_ANNUAL_FEE")
    private String cardAnnualFee;

    @Setter
    @Column(name = "CARD_IMG")
    private String cardImg;

    @Column(name = "CARD_BENEFITS")
    private String cardBenefits;

//    @Column(name = "CARD_BRAND")
//    @Enumerated(EnumType.STRING)
//    private CardBrand cardBrand;


}
