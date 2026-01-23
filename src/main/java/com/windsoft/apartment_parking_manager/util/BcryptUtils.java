package com.windsoft.apartment_parking_manager.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptUtils {

    private BcryptUtils() {}

    public static boolean isValidPassword(String plainText, String encryptedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.matches(plainText, encryptedPassword);
    }
}
