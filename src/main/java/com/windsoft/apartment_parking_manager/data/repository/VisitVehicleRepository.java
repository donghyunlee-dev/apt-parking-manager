package com.windsoft.apartment_parking_manager.data.repository;

import com.windsoft.apartment_parking_manager.data.entity.VisitVehicle;
import com.windsoft.apartment_parking_manager.data.entity.id.VisitVehicleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface VisitVehicleRepository extends JpaRepository<VisitVehicle, VisitVehicleId> {
    Optional<VisitVehicle> findByAptCodeAndVehicleNoAndVisitDateLessThanEqualAndVisitCloseDateGreaterThanEqual(String aptCode, String vehicleNo, LocalDate from, LocalDate to);
    Optional<VisitVehicle> findByAptCodeAndVehicleNo(String aptCode, String vehicleNo);
}
