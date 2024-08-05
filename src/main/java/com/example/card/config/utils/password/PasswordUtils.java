package com.example.card.config.utils.password;

public interface PasswordUtils {
    String passwordHashing(String password, String salt);
    String byteToHex(byte[] bytes);
    String generateSalt();
}
