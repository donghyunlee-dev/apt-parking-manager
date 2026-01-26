package com.windsoft.apartment_parking_manager.controller;

import com.windsoft.apartment_parking_manager.data.dto.RequestContext;
import com.windsoft.apartment_parking_manager.data.dto.VehicleRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.VehicleResponseDto;
import com.windsoft.apartment_parking_manager.data.entity.ParkingVehicle;
import com.windsoft.apartment_parking_manager.data.entity.ResidentVehicle;
import com.windsoft.apartment_parking_manager.data.entity.VisitVehicle;
import com.windsoft.apartment_parking_manager.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/vehicle")
@RestController
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/parking")
    public ResponseEntity<VehicleResponseDto> retrieveParkingVehicle(RequestContext context, @RequestParam String vehicleNo) {

        VehicleRequestDto.ParkingRequest parkingRequest = new VehicleRequestDto.ParkingRequest(context, vehicleNo);

        VehicleResponseDto vehicleInfo = vehicleService.findParkingVehicle(parkingRequest);
        ParkingVehicle parkingVehicle = vehicleService.saveParkingVehicle(parkingRequest, vehicleInfo);

        return ResponseEntity.ok(vehicleInfo);
    }

    @PostMapping("/resident")
    public ResponseEntity<?> registerResidentVehicle(final ResidentVehicle vehicle) {
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping("/visit")
    public ResponseEntity<?> registerVisitVehicle(final VisitVehicle vehicle) {
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping
    public ResponseEntity<?> retrieveVehicles(final Integer vehicleId) {
        return ResponseEntity.ok(vehicleId);
    }

    @GetMapping("/{vehicleNo}")
    public ResponseEntity<?> retrieveVehicleById(final String vehicleNo) {
        return ResponseEntity.ok(vehicleNo);
    }
}
