package com.example.card.domain.dto.request;

import com.example.card.config.constants.CardCheck;
import com.example.card.config.constants.CardTransportation;
import com.example.card.config.utils.password.PasswordUtils;
import com.example.card.domain.entity.Card;
import com.example.card.domain.entity.CardProduct;

import java.util.Date;
import java.util.UUID;

public record CardRequest (
        String cardPassword,
        CardCheck cardCheck,
        String account,
        CardTransportation cardTransportation,
        long cardProductId,
        String cardPaymentDate
) {
    public Card toEntity(
            String cardNumber,
            Boolean cardAble,
            Long cardOneTimeLimit,
            Long cardMonthLimit,
            UUID userId,
            String cardCVC,
            Long cardAnnualFee,
            Date cardCreatedAt,
            String cardName,
            CardProduct cardproduct,
            PasswordUtils passwordUtils


            ) {
        String salt = passwordUtils.generateSalt();
        String hashedPassword = passwordUtils.passwordHashing(cardPassword, salt);
        return Card.builder()
                .account(account)
//                .cardPassword(cardPassword)
//                .passwordUtils.passwordHashing(cardPassword,salt)
                .cardPassword(hashedPassword)
                .cardSalt(salt)
                .cardCheck(cardCheck)
                .cardPaymentDate(cardPaymentDate)
                .cardProduct(cardproduct)
                .userId(userId)
                .cardOneTimeLimit(cardOneTimeLimit)
                .cardMonthLimit(cardMonthLimit)
                .cardNumber(cardNumber)
                .cardCVC(cardCVC)
                .cardAnnualFee(cardAnnualFee)
                .cardAble(cardAble)
                .cardCreatedAt(cardCreatedAt)
                .cardName(cardName)
                .cardTransportation(cardTransportation)
                .build();
    }
}
