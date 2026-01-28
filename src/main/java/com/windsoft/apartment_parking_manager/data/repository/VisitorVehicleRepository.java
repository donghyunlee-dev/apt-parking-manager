package com.windsoft.apartment_parking_manager.data.repository;

import com.windsoft.apartment_parking_manager.data.entity.VisitorVehicle;
import com.windsoft.apartment_parking_manager.data.entity.id.VisitorVehicleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface VisitorVehicleRepository extends JpaRepository<VisitorVehicle, VisitorVehicleId> {
    Optional<VisitorVehicle> findByAptCodeAndVehicleNoAndVisitDateLessThanEqualAndVisitCloseDateGreaterThanEqual(String aptCode, String vehicleNo, LocalDate from, LocalDate to);
    Optional<VisitorVehicle> findByAptCodeAndVehicleNo(String aptCode, String vehicleNo);
    Optional<VisitorVehicle> findById(VisitorVehicleId id);
}
