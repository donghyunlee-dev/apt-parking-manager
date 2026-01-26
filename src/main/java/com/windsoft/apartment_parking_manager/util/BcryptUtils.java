package com.windsoft.apartment_parking_manager.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

public class BcryptUtils {

    private BcryptUtils() {}

    public static String encryptPassword(String password) {

        if (!StringUtils.hasText(password)) {
            return null;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static boolean isValidPassword(String password, String encryptedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, encryptedPassword);
    }
}
