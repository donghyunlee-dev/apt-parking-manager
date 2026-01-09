package com.windsoft.apartment_parking_manager.data.entity;

import com.windsoft.apartment_parking_manager.data.entity.id.ApartmentId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@IdClass(ApartmentId.class)
@Table(name = "apartment")
@Entity
public class Apartment {

    @Id
    private String aptCode;

    private String aptName;

    private String address;

    private Integer building;

    private Integer resident;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
