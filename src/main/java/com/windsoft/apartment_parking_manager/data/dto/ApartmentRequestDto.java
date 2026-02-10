package com.windsoft.apartment_parking_manager.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ApartmentRequestDto {

    @AllArgsConstructor
    @Getter
    public class Verification {
        private String address;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Registration {

        private String aptName;

        private String address;

        private int building;

        private int resident;

        private String finNo;

        private String deviceId;
    }
}
