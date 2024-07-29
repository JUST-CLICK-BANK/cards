package com.example.card.config.utils.card;


import com.example.card.config.constants.CardCheck;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class GenerateCardNumber {

    public static String generateCardNumber(String type) {
         CardCheck cardCheck = CardCheck.getCardCheck(type);
         String cardNumberFront = cardCheck.getValue();
        return cardNumberFront + RandomStringUtils.randomNumeric(16 - cardNumberFront.length());

    }



}
//    System.out.println