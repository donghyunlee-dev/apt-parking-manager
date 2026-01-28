package com.windsoft.apartment_parking_manager.data.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class VisitorVehicleRequestDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegistrationRequest extends RequestContextDto {
        private String vehicleNo;
        private String bdId;
        private String bdUnit;
        private String phone;
        private LocalDate visitDate;
        private LocalTime visitTime;
        private LocalDate visitCloseDate;
        private String memo;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModificationRequest extends RequestContextDto {
        private String bdId;
        private String bdUnit;
        private String phone;
        private LocalDate visitDate;
        private LocalTime visitTime;
        private LocalDate visitCloseDate;
        private String memo;
    }

}
