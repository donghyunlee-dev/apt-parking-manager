package com.windsoft.apartment_parking_manager.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
public final class AES256GcmUtils {

    private AES256GcmUtils() {
        throw new UnsupportedOperationException("Utility Class");
    }

    private static final String SECRET_KEY = "bmY7EltZz350hXtLRCEckrF2bJFbrnC1I3Wd/JTHgeo=";
    private static final String AES_ALG = "AES/GCM/NoPadding";
    private static final String ALGORITHM = "AES";
    private static final Charset ENCODING_TYPE = StandardCharsets.UTF_8;

    private static final int IV_LENGTH = 12;      // GCM 권장
    private static final int TAG_LENGTH_BIT = 128; // 16 bytes

    /**
     * 평문 암호화 시 GCM IV 생성
     * 데이터 암호화 할때마다 생성
     */
    public static byte[] generateIvKey() {
        byte[] iv = new byte[IV_LENGTH];
        new SecureRandom().nextBytes(iv);
        return iv;
    }

    /**
     * AES256 GCM 암호화
     * @param plainText 암호화 대상 평문
     * @return Base64(IV + CipherText + TAG)
     */
    public static String encrypt(String plainText) {
        try {
            byte[] iv = generateIvKey();

            Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, iv);
            byte[] cipherBytes = cipher.doFinal(plainText.getBytes(ENCODING_TYPE));
            // [IV | CipherText+TAG]
            byte[] combined = new byte[iv.length + cipherBytes.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(cipherBytes, 0, combined, iv.length, cipherBytes.length);

            return Base64.getEncoder().encodeToString(combined);
        }
        catch (Exception e) {
            log.error("AES-GCM encrypt failed", e);
            return null;
        }
    }

    /**
     * AES256 GCM 복호화
     * @param encodeText Base64(IV + CipherText + TAG)
     */
    public static String decrypt(String encodeText) {
        try {
            byte[] decoded = Base64.getDecoder().decode(
                    encodeText.getBytes(ENCODING_TYPE)
            );

            byte[] iv = Arrays.copyOfRange(decoded, 0, IV_LENGTH);
            byte[] cipherText = Arrays.copyOfRange(decoded, IV_LENGTH, decoded.length);

            Cipher cipher = getCipher(Cipher.DECRYPT_MODE, iv);
            byte[] plainBytes = cipher.doFinal(cipherText);

            return new String(plainBytes, ENCODING_TYPE);
        }
        catch (AEADBadTagException e) {
            // 변조 감지 (GCM의 핵심)
            log.error("AES-GCM authentication failed (data tampered)", e);
            return null;
        }
        catch (Exception e) {
            log.error("AES-GCM decrypt failed", e);
            return null;
        }
    }

    private static Cipher getCipher(int mode, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ALG);

        SecretKeySpec key = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY.getBytes(ENCODING_TYPE)), ALGORITHM);

        GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
        cipher.init(mode, key, gcmSpec);

        return cipher;
    }
}

