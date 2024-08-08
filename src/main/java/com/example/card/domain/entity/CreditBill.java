package com.example.card.domain.entity;

import com.example.card.domain.type.BillStateType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "CREDIT_BILLS")
public class CreditBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CB_ID")
    private Long billId;

    @Column(name = "CB_AMOUNT", nullable = false)
    private Long billAmount;

    @Column(name = "CB_UNPAID_AMOUNT", nullable = false)
    private Long billUnpaid;

    @Column(name = "CB_STATE", nullable = false)
    @Enumerated(EnumType.STRING)
    private BillStateType billState;

    @Column(name = "CB_DATE", nullable = false)
    private Date billDate;

    @ManyToOne
    @Setter
    @JoinColumn(name = "CARD_ID", nullable = false)
    private Card cardId;

}
