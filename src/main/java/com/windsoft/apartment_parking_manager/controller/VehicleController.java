package com.windsoft.apartment_parking_manager.controller;

import com.windsoft.apartment_parking_manager.data.entity.ParkingVehicle;
import org.springframework.http.ResponseEntity;
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
}
