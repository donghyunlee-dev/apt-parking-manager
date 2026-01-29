package com.windsoft.apartment_parking_manager.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.PropertyNamingStrategy;
import tools.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class VehicleRequestDto {

    @Getter
    public static class VehiclePlateRequest extends RequestContextDto {

        private String vehicleNo;

        public VehiclePlateRequest(final String vehicleNo) {
            this.vehicleNo = vehicleNo.replace(" ", "");
        }
    }

    @ToString
    @Getter
    @Setter
    @NoArgsConstructor
    public static class SearchCondition extends RequestContextDto {
        private String vehicleNo;
        private LocalDate startDate;
        private LocalDate endDate;

        public SearchCondition(final String vehicleNo) {
            this.vehicleNo = vehicleNo.replace(" ", "");
        }
    }

    @Getter
    public static class RegistrationRequest extends RequestContextDto {
        private String vehicleNo;
        private String bdId;
        private String bdUnit;
        private String phone;
        private String memo;

        @Override
        public String toString() {
            return "ResidentRegistrationRequest{" +
                    "bdUnit='" + bdUnit + '\'' +
                    ", phone='" + phone + '\'' +
                    ", bdId='" + bdId + '\'' +
                    ", vehicleNo='" + vehicleNo + '\'' +
                    ", memo='" + memo + '\'' +
                    "}, " +
                    super.toString();
        }
    }

    @Getter
    public static class ModificationRequest extends RequestContextDto {
        private String bdId;
        private String bdUnit;
        private String phone;
        private String memo;

        @Override
        public String toString() {
            return "ResidentRegistrationRequest{" +
                    "bdUnit='" + bdUnit + '\'' +
                    ", phone='" + phone + '\'' +
                    ", bdId='" + bdId + '\'' +
                    ", memo='" + memo + '\'' +
                    "}, " +
                    super.toString();
        }
    }
}
