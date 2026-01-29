package com.windsoft.apartment_parking_manager.data.dto;

import com.windsoft.apartment_parking_manager.data.entity.ResidentVehicle;
import com.windsoft.apartment_parking_manager.data.entity.VisitorVehicle;
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
        private VehicleType status;

        private VehicleInfo() {}

        public static VehicleInfo setData(ResidentVehicle residentVehicle) {
            VehicleInfo vehicleInfo = new VehicleInfo();
            vehicleInfo.aptCode = residentVehicle.getAptCode();
            vehicleInfo.vehicleNo = residentVehicle.getVehicleNo();
            vehicleInfo.bdId = residentVehicle.getBdId();
            vehicleInfo.bdUnit = residentVehicle.getBdUnit();
            vehicleInfo.phone = residentVehicle.getPhone();
            vehicleInfo.status = VehicleType.RESIDENT;
            return vehicleInfo;
        }

        public static VehicleInfo setData(VisitorVehicle visitorVehicle) {
            VehicleInfo vehicleInfo = new VehicleInfo();
            vehicleInfo.aptCode = visitorVehicle.getAptCode();
            vehicleInfo.vehicleNo = visitorVehicle.getVehicleNo();
            vehicleInfo.bdId = visitorVehicle.getBdId();
            vehicleInfo.bdUnit = visitorVehicle.getBdUnit();
            vehicleInfo.phone = visitorVehicle.getPhone();
            vehicleInfo.status = VehicleType.VISITOR;
            return vehicleInfo;
        }

        public static VehicleInfo setData(String aptCode, String vehicleNo) {
            VehicleInfo vehicleInfo = new VehicleInfo();
            vehicleInfo.aptCode = aptCode;
            vehicleInfo.vehicleNo = vehicleNo;
            vehicleInfo.status = VehicleType.ILLEGAL;
            return vehicleInfo;
        }

        public static VehicleInfo noData() {
            return new VehicleInfo();
        }
    }
}
