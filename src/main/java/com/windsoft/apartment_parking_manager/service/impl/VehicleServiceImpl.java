package com.windsoft.apartment_parking_manager.service.impl;

import com.windsoft.apartment_parking_manager.data.dto.VehicleRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.VehicleResponseDto;
import com.windsoft.apartment_parking_manager.data.entity.ResidentVehicle;
import com.windsoft.apartment_parking_manager.data.entity.id.ResidentVehicleId;
import com.windsoft.apartment_parking_manager.data.repository.ResidentVehicleRepository;
import com.windsoft.apartment_parking_manager.service.VehicleService;
import com.windsoft.apartment_parking_manager.type.VehicleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Service
public class VehicleServiceImpl implements VehicleService {

    private final ResidentVehicleRepository residentVehicleRepository;
    @Override
    public VehicleResponseDto findParkingVehicle(VehicleRequestDto.ParkingRequest request) {

        /** TODO
         * 1. 차량 검색
         * 2. 검색 결과로 주차 차량 등록
         * **/
        ResidentVehicle residentVehicle = findResidentVehicle(new ResidentVehicleId(request.getAptCode(), request.getVehicleNo().trim()));

        if (!ObjectUtils.isEmpty(residentVehicle)) {
            return VehicleResponseDto.builder().vehicleNo(residentVehicle.getVehicleNo())
                    .info(residentVehicle.getInfo())
                    .status(VehicleType.RESIDENT.name())
                    .build();
        }

        return null;
    }

    private ResidentVehicle findResidentVehicle(ResidentVehicleId id) {
        return residentVehicleRepository.findById(id).orElse(null);
    }
}
