package com.windsoft.apartment_parking_manager.data.dto;

import com.windsoft.apartment_parking_manager.data.entity.ResidentVehicle;
import com.windsoft.apartment_parking_manager.type.VehicleType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VehicleResponseDto {

    @Builder
    @Getter
    @Setter
    public static class ParkingInfo {
        private String vehicleNo;

        private String info;

        private VehicleType status;
    }

    @Getter
    public static class VehicleInfo {
        private String aptCode;
        private String vehicleNo;
        private String bdId;
        private String bdUnit;
        private String phone;

        private VehicleInfo() {}

        public static VehicleInfo setData(ResidentVehicle residentVehicle) {
            VehicleInfo vehicleInfo = new VehicleInfo();
            vehicleInfo.aptCode = residentVehicle.getAptCode();
            vehicleInfo.vehicleNo = residentVehicle.getVehicleNo();
            vehicleInfo.bdId = residentVehicle.getBdId();
            vehicleInfo.bdUnit = residentVehicle.getBdUnit();
            vehicleInfo.phone = residentVehicle.getPhone();
            return vehicleInfo;
        }
    }
}
