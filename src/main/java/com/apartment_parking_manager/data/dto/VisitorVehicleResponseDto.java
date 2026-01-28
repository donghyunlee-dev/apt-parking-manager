package com.windsoft.apartment_parking_manager.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.windsoft.apartment_parking_manager.data.entity.VisitorVehicle;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

public class VisitorVehicleResponseDto {

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class VisitorVehicleInfo {
        private String aptCode;
        private String vehicleNo;
        private String bdId;
        private String bdUnit;
        private String phone;
        private LocalDate visitDate;
        private LocalTime visitTime;
        private LocalDate visitCloseDate;
        private String memo;

        public static VisitorVehicleInfo setData(VisitorVehicle visitorVehicle) {
            VisitorVehicleInfo info = new VisitorVehicleInfo();
            info.aptCode = visitorVehicle.getAptCode();
            info.vehicleNo = visitorVehicle.getVehicleNo();
            info.bdId = visitorVehicle.getBdId();
            info.bdUnit = visitorVehicle.getBdUnit();
            info.phone = visitorVehicle.getPhone();
            info.visitDate = visitorVehicle.getVisitDate();
            info.visitTime = visitorVehicle.getVisitTime();
            info.visitCloseDate = visitorVehicle.getVisitCloseDate();
            info.memo = visitorVehicle.getMemo();
            return info;
        }
    }
}
