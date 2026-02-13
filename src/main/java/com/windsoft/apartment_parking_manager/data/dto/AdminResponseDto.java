package com.windsoft.apartment_parking_manager.data.dto;

import com.windsoft.apartment_parking_manager.data.entity.Apartment;
import com.windsoft.apartment_parking_manager.data.entity.Bouncer;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AdminResponseDto {

    private String aptCode;

    private String aptName;

    private String address;

    private String adminCode;

    private String adminName;

    private String grade;

    private String result;

    public static AdminResponseDto setData(Apartment apartment, Bouncer bouncer) {
        return AdminResponseDto.builder()
        .aptCode(apartment.getAptCode())
        .aptName(apartment.getAptName())
        .address(apartment.getAddress())
        .adminCode(bouncer.getBouncerCode())
        .adminName(bouncer.getBouncerName())
        .grade(bouncer.getGrade())
        .result("SUCCESS")
        .build();
    }
}
