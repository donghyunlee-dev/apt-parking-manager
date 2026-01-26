package com.windsoft.apartment_parking_manager.data.entity;

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
}
