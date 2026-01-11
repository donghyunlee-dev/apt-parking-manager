package com.windsoft.apartment_parking_manager.data.dto;

import com.windsoft.apartment_parking_manager.data.entity.Apartment;
import lombok.Getter;

@Getter
public class ApartmentResponseDto {

    private String code;

    private String name;

    private String address;

    private Integer buildingCount;

    private Integer residentCount;

    public static ApartmentResponseDto toDto(Apartment apartment) {
        ApartmentResponseDto response = new ApartmentResponseDto();
        response.code = apartment.getAptCode();
        response.name = apartment.getAptName();
        response.address = apartment.getAddress();
        response.buildingCount = apartment.getBuilding();
        response.residentCount = apartment.getResident();
        return response;
    }
}
