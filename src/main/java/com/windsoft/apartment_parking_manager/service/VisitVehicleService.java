package com.windsoft.apartment_parking_manager.service;

import com.windsoft.apartment_parking_manager.data.dto.RequestContext;
import com.windsoft.apartment_parking_manager.data.dto.VisitVehicleDto;

public interface VisitVehicleService {
    VisitVehicleDto.VisitVehicleInfo saveVisitVehicle(VisitVehicleDto.RegistrationRequest registrationRequest);
    VisitVehicleDto.VisitVehicleInfo updateVisitVehicle(String vehicleNo, VisitVehicleDto.ModificationRequest modificationRequest);
    void deleteVisitVehicle(RequestContext context, String vehicleNo);
}
