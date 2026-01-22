package com.windsoft.apartment_parking_manager.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VehicleRequestDto {

    @Getter
    public static class ParkingRequest extends RequestContextDto {

        private String vehicleNo;

        public ParkingRequest(RequestContext context, final String vehicleNo) {
            super(context);
            this.vehicleNo = vehicleNo;
        }
    }

}
