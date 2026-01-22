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
}
