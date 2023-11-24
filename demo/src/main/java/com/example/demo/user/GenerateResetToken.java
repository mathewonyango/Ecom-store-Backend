package com.example.demo.user;

import java.security.SecureRandom;

public class GenerateResetToken {
    public static String generateRandomNumbers(int length) {
        final String DIGITS = "0123456789";
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(DIGITS.length());
            char randomDigit = DIGITS.charAt(randomIndex);
            stringBuilder.append(randomDigit);
        }

        return stringBuilder.toString();
    }

}
