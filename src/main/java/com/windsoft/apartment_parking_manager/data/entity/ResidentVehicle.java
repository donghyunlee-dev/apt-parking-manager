package com.windsoft.apartment_parking_manager.data.entity;

import com.windsoft.apartment_parking_manager.data.dto.VehicleRequestDto;
import com.windsoft.apartment_parking_manager.data.entity.id.ResidentVehicleId;
import com.windsoft.apartment_parking_manager.util.AES256GcmConverter;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@IdClass(ResidentVehicleId.class)
@Table(name = "resident_vehicle")
@Entity
public class ResidentVehicle extends BaseEntity {
    @Id
    private String aptCode;

    @Id
    private String vehicleNo;

    private String bdId;

    private String bdUnit;

    @Convert(converter = AES256GcmConverter.class)
    private String phone;

    private boolean used;

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(bdId);
        sb.append(" ");
        sb.append(bdUnit);
        return sb.toString();
    }

    public void update(VehicleRequestDto.ModificationRequest vehicleInfo) {
        this.bdId = vehicleInfo.getBdId();
        this.bdUnit = vehicleInfo.getBdUnit();
        this.phone = vehicleInfo.getPhone();
        this.updatedId = vehicleInfo.getBouncerCode();
    }

    public void switchUsage() {
        this.used = !this.used;
    }

    public static ResidentVehicle setData(VehicleRequestDto.RegistrationRequest registrationVehicle) {
        ResidentVehicle residentVehicle = new ResidentVehicle();
        residentVehicle.aptCode = registrationVehicle.getAptCode();
        residentVehicle.vehicleNo = registrationVehicle.getVehicleNo();
        residentVehicle.bdId = registrationVehicle.getBdId();
        residentVehicle.bdUnit = registrationVehicle.getBdUnit();
        residentVehicle.phone = registrationVehicle.getPhone();
        residentVehicle.used = true;
        residentVehicle.createdId = registrationVehicle.getBouncerCode();
        return residentVehicle;
    }
}
