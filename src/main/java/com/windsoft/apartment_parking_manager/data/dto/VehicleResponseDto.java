package com.windsoft.apartment_parking_manager.data.dto;

import com.windsoft.apartment_parking_manager.data.entity.ResidentVehicle;
import com.windsoft.apartment_parking_manager.type.HttpType;
import com.windsoft.apartment_parking_manager.type.VehicleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    public static class ResidentVehicleInfo {
        private String aptCode;
        private String vehicleNo;
        private String bdId;
        private String bdUnit;
        private String phone;

        private ResidentVehicleInfo() {}

        public static ResidentVehicleInfo setData(ResidentVehicle residentVehicle) {
            ResidentVehicleInfo residentVehicleInfo = new ResidentVehicleInfo();
            residentVehicleInfo.aptCode = residentVehicle.getAptCode();
            residentVehicleInfo.vehicleNo = residentVehicle.getVehicleNo();
            residentVehicleInfo.bdId = residentVehicle.getBdId();
            residentVehicleInfo.bdUnit = residentVehicle.getBdUnit();
            residentVehicleInfo.phone = residentVehicle.getPhone();
            return residentVehicleInfo;
        }
    }
}
