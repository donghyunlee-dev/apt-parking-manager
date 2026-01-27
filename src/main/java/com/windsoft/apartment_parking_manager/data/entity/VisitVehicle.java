package com.windsoft.apartment_parking_manager.data.entity;

import com.windsoft.apartment_parking_manager.data.dto.VisitVehicleDto;
import com.windsoft.apartment_parking_manager.data.entity.id.VisitVehicleId;
import com.windsoft.apartment_parking_manager.util.AES256GcmConverter;
import jakarta.persistence.*;
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

    public void update(VisitVehicleDto.RegistrationRequest vehicleInfo) {
        this.aptCode = vehicleInfo.getContext().aptCode();
        this.vehicleNo = vehicleInfo.getVehicleNo();
        this.bdId = vehicleInfo.getBdId();
        this.bdUnit = vehicleInfo.getBdUnit();
        this.phone = vehicleInfo.getPhone();
        this.visitDate = vehicleInfo.getVisitDate();
        this.visitTime = vehicleInfo.getVisitTime();
        this.visitCloseDate = vehicleInfo.getVisitCloseDate();
        this.memo = vehicleInfo.getMemo();
        this.createdId = vehicleInfo.getContext().bouncerCode();
    }

    public void update(VisitVehicleDto.ModificationRequest vehicleInfo) {
        this.bdId = vehicleInfo.getBdId();
        this.bdUnit = vehicleInfo.getBdUnit();
        this.phone = vehicleInfo.getPhone();
        this.visitCloseDate = vehicleInfo.getVisitCloseDate();
        this.memo = vehicleInfo.getMemo();
        this.updatedId = vehicleInfo.getContext().bouncerCode();
    }
}
