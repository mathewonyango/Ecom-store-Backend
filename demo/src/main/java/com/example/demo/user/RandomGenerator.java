package com.example.demo.user;

import java.util.Random;
import java.util.UUID;

public class RandomGenerator {

    public static void main(String[] args) {
        // Generate a random number
        int randomNumber = generateRandomNumber();
        System.out.println("Random Number: " + randomNumber);

        // Generate a random string (UUID)
        String randomString = generateRandomString();
        System.out.println("Random String: " + randomString);
    }

    // Method to generate a random number
    private static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(1000); // Adjust the range as needed
    }

    // Method to generate a random string (UUID)
    private static String generateRandomString() {
        return UUID.randomUUID().toString();
    }
}

