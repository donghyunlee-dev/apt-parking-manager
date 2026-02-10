package com.windsoft.apartment_parking_manager.data.dto;

import lombok.Builder;
import lombok.Getter;

public class BouncerRequestDto {

    @Builder
    @Getter
    public static class Registration {

        private String aptCode;

        private String bouncerName;

        private String deviceId;

        private String grade;

        private String finNo;
    }
}
