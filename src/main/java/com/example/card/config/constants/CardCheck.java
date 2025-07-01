package com.example.card.config.constants;


import lombok.Getter;

@Getter
public enum CardCheck {
    CREDIT("credit", "8532"),
    CHECK("check", "2037");
    private final String type;
    private final String value;

    CardCheck(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public static CardCheck getCardCheck(String type) {
        for (CardCheck cardCheck : CardCheck.values()) {
            if (cardCheck.getType().equalsIgnoreCase(type)) {
                return cardCheck;
            }
        }
        throw new IllegalArgumentException("Unknown card type: " + type);
    }
}

