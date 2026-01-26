package com.windsoft.apartment_parking_manager.controller;

import com.windsoft.apartment_parking_manager.data.dto.BaseResponseDto;
import com.windsoft.apartment_parking_manager.data.dto.RequestContext;
import com.windsoft.apartment_parking_manager.data.dto.VehicleRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.VehicleResponseDto;
import com.windsoft.apartment_parking_manager.service.VehicleService;
import com.windsoft.apartment_parking_manager.type.HttpType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/vehicle")
@RestController
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/parking")
    public ResponseEntity<VehicleResponseDto.ParkingInfo> retrieveParkingVehicle(RequestContext context, @RequestParam String vehicleNo) {

        VehicleRequestDto.VehiclePlateRequest vehiclePlateRequest = new VehicleRequestDto.VehiclePlateRequest(vehicleNo);
        vehiclePlateRequest.setContext(context);

        VehicleResponseDto.ParkingInfo vehicleInfo = vehicleService.findParkingVehicle(vehiclePlateRequest);
        vehicleService.saveParkingVehicleLog(vehiclePlateRequest, vehicleInfo);

        return ResponseEntity.ok(vehicleInfo);
    }

    @PostMapping("/resident")
    public ResponseEntity<BaseResponseDto<?>> registerResidentVehicle(RequestContext context, @RequestBody VehicleRequestDto.ResidentRegistrationRequest resident) {
        resident.setContext(context);

        VehicleResponseDto.ResidentVehicleInfo residentVehicle = vehicleService.saveResidentVehicle(resident);

        if (residentVehicle == null) {
            return new ResponseEntity<>(new BaseResponseDto(residentVehicle, HttpType.DUPLICATE_RESOURCE), HttpType.DUPLICATE_RESOURCE.getHttpStatus());
        }

        return new ResponseEntity<>(new BaseResponseDto(residentVehicle), HttpStatus.OK);
    }

    @PutMapping("/{vehicle_no}")
    public ResponseEntity<BaseResponseDto<?>> modifyResidentVehicle(RequestContext context
            , final @RequestParam(name = "vehicle_no") String vehicleNo
            , @RequestBody VehicleRequestDto.ResidentModificationRequest resident) {

        resident.setContext(context);

        VehicleResponseDto.ResidentVehicleInfo residentVehicle = vehicleService.updateResidentVehicle(vehicleNo, resident);

        return new ResponseEntity<>(new BaseResponseDto(residentVehicle), HttpStatus.OK);
    }

    @PatchMapping("/{vehicle_no}")
    public ResponseEntity<BaseResponseDto<?>> switchUsageResidentVehicle(RequestContext context, final @RequestParam(name = "vehicle_no") String vehicleNo) {
        VehicleRequestDto.VehiclePlateRequest vehiclePlateRequest = new VehicleRequestDto.VehiclePlateRequest(vehicleNo);
        vehiclePlateRequest.setContext(context);
        VehicleResponseDto.ResidentVehicleInfo residentVehicle = vehicleService.changeUsageResidentVehicle(vehiclePlateRequest);
        return new ResponseEntity<>(new BaseResponseDto(residentVehicle), HttpStatus.OK);
    }

    @PostMapping("/visit")
    public ResponseEntity<?> registerVisitVehicle(RequestContext context, final VehicleRequestDto.ResidentRegistrationRequest resident) {
        System.out.println(resident.toString());
        return ResponseEntity.ok(resident);
    }

    @GetMapping
    public ResponseEntity<?> retrieveVehicles(RequestContext context, final Integer vehicleId) {
        return ResponseEntity.ok(vehicleId);
    }

    @GetMapping("/{vehicleNo}")
    public ResponseEntity<?> retrieveVehicleById(final String vehicleNo) {
        return ResponseEntity.ok(vehicleNo);
    }
}
