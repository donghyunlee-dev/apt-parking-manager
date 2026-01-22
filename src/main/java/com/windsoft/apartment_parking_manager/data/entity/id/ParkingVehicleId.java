package com.windsoft.apartment_parking_manager.data.entity.id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@SequenceGenerator(
    name = "parking_vehicle_seq",
    sequenceName = "parking_vehicle_seq",
    allocationSize = 1
)
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingVehicleId implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "parking_vehicle_seq"
    )
    private long seqNo;
}
