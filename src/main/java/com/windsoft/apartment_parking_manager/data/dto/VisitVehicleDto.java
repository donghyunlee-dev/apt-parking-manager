package com.windsoft.apartment_parking_manager.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.windsoft.apartment_parking_manager.data.entity.VisitVehicle;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class VisitVehicleDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegistrationRequest {
        private String vehicleNo;
        private String bdId;
        private String bdUnit;
        private String phone;
        private LocalDate visitDate;
        private LocalTime visitTime;
        private LocalDate visitCloseDate;
        private String memo;
        private RequestContext context;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModificationRequest {
        private String bdId;
        private String bdUnit;
        private String phone;
        private LocalDate visitCloseDate;
        private String memo;
        private RequestContext context;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class VisitVehicleInfo {
        private String aptCode;
        private String vehicleNo;
        private String bdId;
        private String bdUnit;
        private String phone;
        private LocalDate visitDate;
        private LocalTime visitTime;
        private LocalDate visitCloseDate;
        private String memo;

        public VisitVehicleInfo(VisitVehicle visitVehicle) {
            this.aptCode = visitVehicle.getAptCode();
            this.vehicleNo = visitVehicle.getVehicleNo();
            this.bdId = visitVehicle.getBdId();
            this.bdUnit = visitVehicle.getBdUnit();
            this.phone = visitVehicle.getPhone();
            this.visitDate = visitVehicle.getVisitDate();
            this.visitTime = visitVehicle.getVisitTime();
            this.visitCloseDate = visitVehicle.getVisitCloseDate();
            this.memo = visitVehicle.getMemo();
        }
    }
}
