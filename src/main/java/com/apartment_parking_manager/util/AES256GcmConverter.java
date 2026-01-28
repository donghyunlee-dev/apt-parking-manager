package com.windsoft.apartment_parking_manager.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.StringUtils;

@Converter
public class AES256GcmConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String plainText) {

        if (!StringUtils.hasText(plainText)) {
            return "";
        }

        return AES256GcmUtils.encrypt(plainText);
    }

    @Override
    public String convertToEntityAttribute(String encryptedText) {

        if (!StringUtils.hasText(encryptedText)) {
            return "";
        }

        return AES256GcmUtils.decrypt(encryptedText);
    }
}
