package com.windsoft.apartment_parking_manager.data.entity;

import com.windsoft.apartment_parking_manager.data.entity.id.ParkingVehicleId;
import com.windsoft.apartment_parking_manager.type.VehicleType;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@IdClass(ParkingVehicleId.class)
@Table(name = "parking_vehicle")
@Entity
public class ParkingVehicle {
    @Id
    private long seqNo;

    private String aptCode;

    private String bouncerCode;

    private String vehicleNo;
    @Enumerated(EnumType.STRING)
    private VehicleType type;

    private String location;

    private String deviceId;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = ZonedDateTime.now(ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.of("Asia/Seoul"))
                .toLocalDateTime();
    }
}
