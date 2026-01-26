package com.windsoft.apartment_parking_manager.service;

import com.windsoft.apartment_parking_manager.data.dto.VehicleRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.VehicleResponseDto;

public interface VehicleService {

    VehicleResponseDto.ParkingInfo findParkingVehicle(VehicleRequestDto.VehiclePlateRequest request);

    void saveParkingVehicleLog(VehicleRequestDto.VehiclePlateRequest request, VehicleResponseDto.ParkingInfo vehicleInfo);

    VehicleResponseDto.ResidentVehicleInfo saveResidentVehicle(VehicleRequestDto.ResidentRegistrationRequest request);

    VehicleResponseDto.ResidentVehicleInfo updateResidentVehicle(String vehicleNo, VehicleRequestDto.ResidentModificationRequest request);

    VehicleResponseDto.ResidentVehicleInfo changeUsageResidentVehicle(VehicleRequestDto.VehiclePlateRequest request);
}
