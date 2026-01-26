package com.windsoft.apartment_parking_manager.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.MessageFormat;

public class PasswordUtils {

    @Test
    void createPasswordBcrypt() {
        String testPassword = "123456";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(testPassword);
        System.out.println(MessageFormat.format("hash : {0}", hash));

        boolean matches = encoder.matches(testPassword, hash);

        Assertions.assertTrue(matches);
    }
}
