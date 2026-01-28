package com.windsoft.apartment_parking_manager.data.repository;

import com.windsoft.apartment_parking_manager.data.entity.ResidentVehicle;
import com.windsoft.apartment_parking_manager.data.entity.id.ResidentVehicleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentVehicleRepository extends JpaRepository<ResidentVehicle, ResidentVehicleId> {
}
