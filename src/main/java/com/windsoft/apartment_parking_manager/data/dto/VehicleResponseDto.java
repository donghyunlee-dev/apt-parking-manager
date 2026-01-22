package com.windsoft.apartment_parking_manager.data.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VehicleResponseDto {

    private String vehicleNo;

    private String info;

    private String status;
}
