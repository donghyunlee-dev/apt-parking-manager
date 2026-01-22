package com.windsoft.apartment_parking_manager.data.entity.id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VisitVehicleId {
    private String aptCode;
    private String vehicleNo;
    private LocalDate visitDate;
}
