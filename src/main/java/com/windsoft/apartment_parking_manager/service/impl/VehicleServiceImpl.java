package com.windsoft.apartment_parking_manager.service.impl;

import com.windsoft.apartment_parking_manager.data.dto.VehicleRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.VehicleResponseDto;
import com.windsoft.apartment_parking_manager.data.entity.ParkingVehicle;
import com.windsoft.apartment_parking_manager.data.entity.ResidentVehicle;
import com.windsoft.apartment_parking_manager.data.entity.id.ResidentVehicleId;
import com.windsoft.apartment_parking_manager.data.repository.ParkingVehicleRepository;
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
    private final ParkingVehicleRepository parkingVehicleRepository;

    @Override
    public VehicleResponseDto findParkingVehicle(VehicleRequestDto.ParkingRequest request) {

        ResidentVehicle residentVehicle = findResidentVehicle(new ResidentVehicleId(request.getAptCode(), request.getVehicleNo()));

        if (!ObjectUtils.isEmpty(residentVehicle)) {
            return VehicleResponseDto.builder().vehicleNo(residentVehicle.getVehicleNo())
                    .info(residentVehicle.getInfo())
                    .status(VehicleType.RESIDENT)
                    .build();
        }

        return VehicleResponseDto.builder().vehicleNo(request.getVehicleNo())
                .info("미등록 차량")
                .status(VehicleType.ILLEGAL)
                .build();
    }

    @Override
    public ParkingVehicle saveParkingVehicle(VehicleRequestDto.ParkingRequest request, VehicleResponseDto vehicleInfo) {
        return parkingVehicleRepository.save(ParkingVehicle.setData(request, vehicleInfo));
    }

    private ResidentVehicle findResidentVehicle(ResidentVehicleId id) {
        return residentVehicleRepository.findById(id).orElse(null);
    }
}
