package com.windsoft.apartment_parking_manager.data.repository;

import com.windsoft.apartment_parking_manager.data.entity.VisitVehicle;
import com.windsoft.apartment_parking_manager.data.entity.id.VisitVehicleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitVehicleRepository extends JpaRepository<VisitVehicle, VisitVehicleId> {
}
