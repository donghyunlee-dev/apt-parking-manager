package com.windsoft.apartment_parking_manager.service.impl;

import com.windsoft.apartment_parking_manager.data.dto.VehicleRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.VehicleResponseDto;
import com.windsoft.apartment_parking_manager.data.entity.ParkingVehicle;
import com.windsoft.apartment_parking_manager.data.entity.ResidentVehicle;
import com.windsoft.apartment_parking_manager.data.entity.VisitVehicle;
import com.windsoft.apartment_parking_manager.data.entity.id.ResidentVehicleId;
import com.windsoft.apartment_parking_manager.data.entity.id.VisitVehicleId;
import com.windsoft.apartment_parking_manager.data.repository.ParkingVehicleRepository;
import com.windsoft.apartment_parking_manager.data.repository.ResidentVehicleRepository;
import com.windsoft.apartment_parking_manager.data.repository.VisitVehicleRepository;
import com.windsoft.apartment_parking_manager.service.VehicleService;
import com.windsoft.apartment_parking_manager.type.VehicleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class VehicleServiceImpl implements VehicleService {

    private final ResidentVehicleRepository residentVehicleRepository;
    private final VisitVehicleRepository visitVehicleRepository;
    private final ParkingVehicleRepository parkingVehicleRepository;

    @Transactional(readOnly = true)
    @Override
    public VehicleResponseDto.ParkingInfo findParkingVehicle(VehicleRequestDto.VehiclePlateRequest request) {

        ResidentVehicle residentVehicle = findResidentVehicle(new ResidentVehicleId(request.getAptCode(), request.getVehicleNo()));

        if (!ObjectUtils.isEmpty(residentVehicle)) {
            return VehicleResponseDto.ParkingInfo.builder()
                    .vehicleNo(residentVehicle.getVehicleNo())
                    .info(residentVehicle.getInfo())
                    .status(VehicleType.RESIDENT)
                    .build();
        }

        VisitVehicle visitVehicle = findVisitVehicle(new VisitVehicleId(request.getAptCode(), request.getVehicleNo(), LocalDate.now()));

        if (!ObjectUtils.isEmpty(visitVehicle)) {
            return VehicleResponseDto.ParkingInfo.builder()
                    .vehicleNo(visitVehicle.getVehicleNo())
                    .info(visitVehicle.getInfo())
                    .status(VehicleType.VISIT)
                    .build();
        }

        return VehicleResponseDto.ParkingInfo.builder()
                .vehicleNo(request.getVehicleNo())
                .info("미등록 차량")
                .status(VehicleType.ILLEGAL)
                .build();
    }

    @Transactional
    @Override
    public void saveParkingVehicleLog(VehicleRequestDto.VehiclePlateRequest request, VehicleResponseDto.ParkingInfo vehicleInfo) {
        parkingVehicleRepository.save(ParkingVehicle.setData(request, vehicleInfo));
    }

    @Transactional
    @Override
    public VehicleResponseDto.ResidentVehicleInfo saveResidentVehicle(VehicleRequestDto.ResidentRegistrationRequest request) {

        ResidentVehicle residentVehicle = findResidentVehicle(new ResidentVehicleId(request.getAptCode(), request.getVehicleNo()));

        if (residentVehicle != null) {
            return null;
        }

        ResidentVehicle savedResidentVehicle = residentVehicleRepository.save(ResidentVehicle.setData(request));
        return VehicleResponseDto.ResidentVehicleInfo.setData(savedResidentVehicle);
    }

    @Transactional
    @Override
    public VehicleResponseDto.ResidentVehicleInfo updateResidentVehicle(String vehicleNo, VehicleRequestDto.ResidentModificationRequest request) {

        ResidentVehicle residentVehicle = findResidentVehicle(new ResidentVehicleId(request.getAptCode(), vehicleNo));

        residentVehicle.update(request);

        return VehicleResponseDto.ResidentVehicleInfo.setData(residentVehicle);
    }

    @Transactional
    @Override
    public VehicleResponseDto.ResidentVehicleInfo changeUsageResidentVehicle(VehicleRequestDto.VehiclePlateRequest request) {

        ResidentVehicle residentVehicle = findResidentVehicle(new ResidentVehicleId(request.getAptCode(), request.getVehicleNo()));
        residentVehicle.switchUsage();

        return VehicleResponseDto.ResidentVehicleInfo.setData(residentVehicle);
    }

    @Override
    public void deleteResidentVehicle(VehicleRequestDto.VehiclePlateRequest request) {
        residentVehicleRepository.deleteById(new ResidentVehicleId(request.getAptCode(), request.getVehicleNo()));
    }

    private ResidentVehicle findResidentVehicle(ResidentVehicleId id) {
        return residentVehicleRepository.findById(id).orElse(null);
    }

    private VisitVehicle findVisitVehicle(VisitVehicleId id) {
        return visitVehicleRepository.findByAptCodeAndVehicleNoAndVisitDateLessThanEqualAndVisitCloseDateGreaterThanEqual(
                id.getAptCode(),
                id.getVehicleNo(),
                id.getVisitDate(),
                id.getVisitDate()

        ).orElse(null);
    }
}
