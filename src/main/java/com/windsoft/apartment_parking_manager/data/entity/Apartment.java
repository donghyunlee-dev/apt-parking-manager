package com.windsoft.apartment_parking_manager.data.entity;

import com.windsoft.apartment_parking_manager.data.entity.id.ApartmentId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Table(name = "apartment")
@Entity
public class Apartment {

    @EmbeddedId
    private ApartmentId id;

    private String aptName;

    private String address;

    private Integer building;

    private Integer resident;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
