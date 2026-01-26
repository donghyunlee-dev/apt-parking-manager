package com.windsoft.apartment_parking_manager.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum HttpType {
    RESOURCE_NOT_FOUND(NOT_FOUND, "데이터를 찾을 수 없습니다"),
    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다"),
    SUCCESS(OK, "정상적으로 처리되었습니다")

    ;

    private final HttpStatus httpStatus;
    private final String message;

    public static HttpType resolve(int httpCode) {

        for (HttpType type : HttpType.values()) {
            if(httpCode == type.getHttpStatus().value()) {
                return type;
            }
        }
        return null;
    }
}
