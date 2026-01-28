package com.windsoft.apartment_parking_manager.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public final class CustomObjectUtils {

    private CustomObjectUtils() {
        throw new UnsupportedOperationException("Utility class");
    }
    public static <T> T deepCopy(T source, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            return mapper.readValue(mapper.writeValueAsString(source), clazz);
        } catch (IOException e) {
            throw new RuntimeException("복사 실패", e);
        }
    }
}
