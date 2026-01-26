package com.windsoft.apartment_parking_manager.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

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


    @Getter
    public static class ResidentRegistrationRequest extends RequestContextDto {
        private String vehicleNo;
        private String bdId;
        private String bdUnit;
        private String phone;

        @Override
        public String toString() {
            return "ResidentRegistrationRequest{" +
                    "bdUnit='" + bdUnit + '\'' +
                    ", phone='" + phone + '\'' +
                    ", bdId='" + bdId + '\'' +
                    ", vehicleNo='" + vehicleNo + '\'' +
                    "}, " +
                    super.toString();
        }
    }

    @Getter
    public static class ResidentModificationRequest extends RequestContextDto {
        private String bdId;
        private String bdUnit;
        private String phone;

        @Override
        public String toString() {
            return "ResidentRegistrationRequest{" +
                    "bdUnit='" + bdUnit + '\'' +
                    ", phone='" + phone + '\'' +
                    ", bdId='" + bdId + '\'' +
                    "}, " +
                    super.toString();
        }
    }
}
