package com.windsoft.apartment_parking_manager.data.repository;

import com.windsoft.apartment_parking_manager.data.entity.ParkingVehicle;
import com.windsoft.apartment_parking_manager.data.entity.id.ParkingVehicleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingVehicleRepository extends JpaRepository<ParkingVehicle, ParkingVehicleId> {
}
