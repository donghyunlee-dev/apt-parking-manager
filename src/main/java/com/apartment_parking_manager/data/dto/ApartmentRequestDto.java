package com.windsoft.apartment_parking_manager.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ApartmentRequestDto {

    @AllArgsConstructor
    @Getter
    public class verify {
        private String address;
    }
}
