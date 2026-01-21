package com.windsoft.apartment_parking_manager.controller;

import com.windsoft.apartment_parking_manager.data.entity.ParkingVehicle;
import com.windsoft.apartment_parking_manager.data.entity.ResidentVehicle;
import com.windsoft.apartment_parking_manager.data.entity.VisitVehicle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/vehicle")
@RestController
public class VehicleController {

    @PostMapping("/parking")
    public ResponseEntity<?> recordParkingVehicle(final ParkingVehicle vehicle) {
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping("/resident")
    public ResponseEntity<?> addResidentVehicle(final ResidentVehicle vehicle) {
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping("/visit")
    public ResponseEntity<?> addVisitVehicle(final VisitVehicle vehicle) {
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping
    public ResponseEntity<?> retrieveVehicles(final Integer vehicleId) {
        return ResponseEntity.ok(vehicleId);
    }

    @GetMapping("/{no}")
    public ResponseEntity<?> retrieveVehicleById(final String vehicleNo) {
        return ResponseEntity.ok(vehicleNo);
    }
}
