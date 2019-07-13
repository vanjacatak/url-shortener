package com.vanjacatak.infobip.urlshortener.shorturltwo;

public class RandomStringGenerator {

    private static String getRandomChars(int num) {
        String randomStr = "";
        String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < num ; i++)
            randomStr += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()));

        return randomStr;

    }

    static String generatePassword() {

        return getRandomChars(8);
    }

    static String generateShortUrl() {

        return getRandomChars(5);
    }

}
