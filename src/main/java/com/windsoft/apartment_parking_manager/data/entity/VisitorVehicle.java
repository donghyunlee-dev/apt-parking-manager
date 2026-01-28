package com.windsoft.apartment_parking_manager.data.entity;

import com.windsoft.apartment_parking_manager.data.dto.VisitorVehicleRequestDto;
import com.windsoft.apartment_parking_manager.data.entity.id.VisitorVehicleId;
import com.windsoft.apartment_parking_manager.util.AES256GcmConverter;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@IdClass(VisitorVehicleId.class)
@Table(name = "visitor_vehicle")
@Entity
public class VisitorVehicle extends BaseEntity {
    @Id
    private String aptCode;
    @Id
    private String vehicleNo;

    private String bdId;

    private String bdUnit;

    @Convert(converter = AES256GcmConverter.class)
    private String phone;
    @Id
    private LocalDate visitDate;

    private LocalTime visitTime;

    private LocalDate visitCloseDate;

    private String memo;

    public String getInfo() {
        StringBuilder sb = new StringBuilder();

        sb.append(bdId);
        sb.append(" ");
        sb.append(bdUnit);
        sb.append("\t");
        sb.append(visitDate);
        sb.append("~");
        sb.append(visitCloseDate);
        sb.append("\t");
        sb.append(phone);
        sb.append("\t");
        sb.append(memo);
        return sb.toString();
    }

    public void update(VisitorVehicleRequestDto.ModificationRequest vehicleInfo) {
        this.bdId = vehicleInfo.getBdId();
        this.bdUnit = vehicleInfo.getBdUnit();
        this.phone = vehicleInfo.getPhone();
        this.visitCloseDate = vehicleInfo.getVisitCloseDate();
        this.memo = vehicleInfo.getMemo();
        this.updatedId = vehicleInfo.getBouncerCode();
    }

    public static VisitorVehicle setData(VisitorVehicleRequestDto.RegistrationRequest vehicleInfo) {
        VisitorVehicle vehicle = new VisitorVehicle();
        vehicle.aptCode = vehicleInfo.getAptCode();
        vehicle.vehicleNo = vehicleInfo.getVehicleNo();
        vehicle.bdId = vehicleInfo.getBdId();
        vehicle.bdUnit = vehicleInfo.getBdUnit();
        vehicle.phone = vehicleInfo.getPhone();
        vehicle.visitDate = vehicleInfo.getVisitDate();
        vehicle.visitTime = vehicleInfo.getVisitTime();
        vehicle.visitCloseDate = vehicleInfo.getVisitCloseDate();
        vehicle.memo = vehicleInfo.getMemo();
        vehicle.createdId = vehicleInfo.getBouncerCode();
        return vehicle;
    }
}
