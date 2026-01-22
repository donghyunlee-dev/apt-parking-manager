package com.windsoft.apartment_parking_manager.service;

import com.windsoft.apartment_parking_manager.data.dto.VehicleRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.VehicleResponseDto;

public interface VehicleService {

    VehicleResponseDto findParkingVehicle(VehicleRequestDto.ParkingRequest request);
}
