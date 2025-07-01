package com.example.card.config.utils.card;

import java.util.random.RandomGenerator;

public class CVC {
   public static String generateCVC(){
       RandomGenerator random = RandomGenerator.getDefault();
       int cvc = random.nextInt(1000);
       return String.format("%03d", cvc);
   }
}
