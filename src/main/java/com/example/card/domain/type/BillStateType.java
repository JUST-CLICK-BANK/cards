package com.example.card.domain.type;

public enum BillStateType {
    WAITING("결제 대기중"),
    FULL_PAYMENT("완납됨"),
    NON_PAYMENT("미납됨");

    BillStateType(String s) {
    }
}
