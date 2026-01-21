package com.windsoft.apartment_parking_manager.data.entity;

import com.windsoft.apartment_parking_manager.data.entity.id.ResidentVehicleId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
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

    private String phone;

    private boolean used;
}
