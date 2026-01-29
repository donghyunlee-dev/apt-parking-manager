package com.windsoft.apartment_parking_manager.controller;

import com.windsoft.apartment_parking_manager.annotation.SnakCaseModelAttribute;
import com.windsoft.apartment_parking_manager.data.dto.*;
import com.windsoft.apartment_parking_manager.service.VehicleService;
import com.windsoft.apartment_parking_manager.type.HttpType;
import com.windsoft.apartment_parking_manager.type.VehicleType;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RequestMapping("/api/vehicles")
@RestController
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/parking")
    public ResponseEntity<VehicleResponseDto.ParkingInfo> retrieveParkingVehicle(RequestContext context, @RequestParam(name = "vehicle_no") String vehicleNo) {

        VehicleRequestDto.VehiclePlateRequest vehiclePlateRequest = new VehicleRequestDto.VehiclePlateRequest(vehicleNo);
        vehiclePlateRequest.setContext(context);

        VehicleResponseDto.ParkingInfo vehicleInfo = vehicleService.findParkingVehicle(vehiclePlateRequest);
        vehicleService.saveParkingVehicleLog(vehiclePlateRequest, vehicleInfo);

        return ResponseEntity.ok(vehicleInfo);
    }

    @PostMapping("/residents")
    public ResponseEntity<BaseResponseDto<?>> registerResidentVehicle(RequestContext context, @RequestBody VehicleRequestDto.RegistrationRequest resident) {
        resident.setContext(context);

        VehicleResponseDto.VehicleInfo residentVehicle = vehicleService.saveResidentVehicle(resident);

        if (residentVehicle == null) {
            return new ResponseEntity<>(new BaseResponseDto(residentVehicle, HttpType.DUPLICATE_RESOURCE), HttpType.DUPLICATE_RESOURCE.getHttpStatus());
        }

        return new ResponseEntity<>(new BaseResponseDto(residentVehicle), HttpStatus.OK);
    }

    @PutMapping("/residents/{vehicle_no}")
    public ResponseEntity<BaseResponseDto<?>> modifyResidentVehicle(RequestContext context
            , @PathVariable(name = "vehicle_no") String vehicleNo
            , @RequestBody VehicleRequestDto.ModificationRequest resident) {

        resident.setContext(context);

        VehicleResponseDto.VehicleInfo residentVehicle = vehicleService.updateResidentVehicle(vehicleNo, resident);

        return new ResponseEntity<>(new BaseResponseDto(residentVehicle), HttpStatus.OK);
    }

    @PatchMapping("/residents/{vehicle_no}")
    public ResponseEntity<BaseResponseDto<?>> switchUsageResidentVehicle(RequestContext context, @PathVariable(name = "vehicle_no") String vehicleNo) {
        VehicleRequestDto.VehiclePlateRequest vehiclePlateRequest = new VehicleRequestDto.VehiclePlateRequest(vehicleNo);
        vehiclePlateRequest.setContext(context);
        VehicleResponseDto.VehicleInfo residentVehicle = vehicleService.changeUsageResidentVehicle(vehiclePlateRequest);
        return new ResponseEntity<>(new BaseResponseDto(residentVehicle), HttpStatus.OK);
    }

    @DeleteMapping("/residents/{vehicle_no}")
    public ResponseEntity<BaseResponseDto<?>> removeResidentVehicle(RequestContext context, @PathVariable(name = "vehicle_no") String vehicleNo) {
        VehicleRequestDto.VehiclePlateRequest vehiclePlateRequest = new VehicleRequestDto.VehiclePlateRequest(vehicleNo);
        vehiclePlateRequest.setContext(context);
        vehicleService.deleteResidentVehicle(vehiclePlateRequest);
        return new ResponseEntity<>(new BaseResponseDto("삭제되었습니다."), HttpStatus.OK);
    }

    @PostMapping("/visitors")
    public ResponseEntity<BaseResponseDto<?>> registerVisitorVehicle(RequestContext context, @RequestBody VisitorVehicleRequestDto.RegistrationRequest visitorInfo) {
        visitorInfo.setContext(context);
        VisitorVehicleResponseDto.VisitorVehicleInfo visitorVehicle = vehicleService.saveVisitVehicle(visitorInfo);

        if (visitorVehicle == null) {
            return new ResponseEntity<>(new BaseResponseDto(visitorVehicle, HttpType.DUPLICATE_RESOURCE), HttpType.DUPLICATE_RESOURCE.getHttpStatus());
        }

        return new ResponseEntity<>(new BaseResponseDto(visitorVehicle), HttpStatus.OK);
    }

    @PutMapping("/visitors/{vehicle_no}")
    public ResponseEntity<BaseResponseDto<?>> modifyVisitorVehicle(RequestContext context
            , @PathVariable(name = "vehicle_no") String vehicleNo
            , @RequestBody VisitorVehicleRequestDto.ModificationRequest visitorInfo) {

        visitorInfo.setContext(context);
        VisitorVehicleResponseDto.VisitorVehicleInfo visitVehicle = vehicleService.updateVisitVehicle(vehicleNo, visitorInfo);

        return new ResponseEntity<>(new BaseResponseDto(visitVehicle), HttpStatus.OK);
    }

    @DeleteMapping("/visitors/{vehicle_no}/{visit_date}")
    public ResponseEntity<BaseResponseDto<?>> removeVisitorVehicle(RequestContext context
            , @PathVariable(name = "vehicle_no") String vehicleNo
            , @PathVariable(name = "visit_date") LocalDate visitDate) {
        vehicleService.deleteVisitorVehicle(context, vehicleNo, visitDate);
        return new ResponseEntity<>(new BaseResponseDto("삭제되었습니다."), HttpStatus.OK);
    }

    @GetMapping("/{vehicle_no}")
    public ResponseEntity<?> findVehicle(RequestContext context, @PathVariable(name = "vehicle_no") VehicleRequestDto.VehiclePlateRequest request) {
        request.setContext(context);
        System.out.println(request);
        VehicleResponseDto.VehicleInfo vehicle = vehicleService.getVehicle(request);

        return ResponseEntity.ok(new BaseResponseDto<>(vehicle, (ObjectUtils.isEmpty(vehicle.getVehicleNo()) ? "0건" : "1건") + "이 검색되었습니다."));
    }

    @GetMapping
    public ResponseEntity<?> retrieveVehicles(RequestContext context, @SnakCaseModelAttribute VehicleRequestDto.SearchCondition condition) {
        /**
         * 검색 조건 - VehicleType, Period, Vehicle No
         */
        condition.setContext(context);
        System.out.println(condition);

        return ResponseEntity.ok(condition);
    }

    @GetMapping("/{vehicleNo}/{type}")
    public ResponseEntity<?> retrieveVehicleById(final String vehicleNo, final VehicleType type) {
        return ResponseEntity.ok(vehicleNo);
    }
}
