package com.windsoft.apartment_parking_manager.data.entity;

import com.windsoft.apartment_parking_manager.data.entity.id.VisitVehicleId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@IdClass(VisitVehicleId.class)
@Table(name = "visit_vehicle")
@Entity
public class VisitVehicle extends BaseEntity {
    @Id
    private String aptCode;
    @Id
    private String vehicleNo;

    private String bdId;

    private String bdUnit;

    private String phone;

    private LocalDate visitDate;

    private LocalTime visitTime;

    private LocalDate visitCloseDate;

    private String memo;


}

