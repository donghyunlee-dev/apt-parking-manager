package com.windsoft.apartment_parking_manager.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("dev")
@SpringBootTest
class AES256GcmUtilsTest {

    @Test
    void testEncrypt() {
        String phone = "010-5335-5548";

        String encrypt = AES256GcmUtils.encrypt(phone);
        System.out.println(encrypt);
        String decrypt = AES256GcmUtils.decrypt(encrypt);
        System.out.println(decrypt);

        Assertions.assertEquals(phone, decrypt);
    }
}