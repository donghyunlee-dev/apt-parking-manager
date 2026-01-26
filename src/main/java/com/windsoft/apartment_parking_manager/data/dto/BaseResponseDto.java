package com.windsoft.apartment_parking_manager.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.windsoft.apartment_parking_manager.type.HttpType;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
public class BaseResponseDto<T> {

    private final T payload;

    private final long timestamp = System.currentTimeMillis();

    private final String message;

    public BaseResponseDto(final T payload) {
        this.payload = payload;
        this.message = "정상적으로 처리되었습니다.";
    }

    public BaseResponseDto(final String message) {
        this.payload = null;
        this.message = message;
    }

    public BaseResponseDto(final T paylaod, final String message) {
        this.payload = paylaod;
        this.message = message;
    }

    public BaseResponseDto(final T paylaod, final HttpType error) {
        this.payload = paylaod;
        this.message = error.getMessage();
    }
}
