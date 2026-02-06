package com.windsoft.apartment_parking_manager.service.impl;

import com.windsoft.apartment_parking_manager.data.dto.*;
import com.windsoft.apartment_parking_manager.data.entity.ParkingVehicle;
import com.windsoft.apartment_parking_manager.data.entity.ResidentVehicle;
import com.windsoft.apartment_parking_manager.data.entity.VisitorVehicle;
import com.windsoft.apartment_parking_manager.data.entity.id.ParkingVehicleId;
import com.windsoft.apartment_parking_manager.data.entity.id.ResidentVehicleId;
import com.windsoft.apartment_parking_manager.data.entity.id.VisitorVehicleId;
import com.windsoft.apartment_parking_manager.data.repository.ParkingVehicleRepository;
import com.windsoft.apartment_parking_manager.data.repository.ResidentVehicleRepository;
import com.windsoft.apartment_parking_manager.data.repository.VisitorVehicleRepository;
import com.windsoft.apartment_parking_manager.service.VehicleService;
import com.windsoft.apartment_parking_manager.type.VehicleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VehicleServiceImpl implements VehicleService {

    private final ResidentVehicleRepository residentVehicleRepository;
    private final VisitorVehicleRepository visitorVehicleRepository;
    private final ParkingVehicleRepository parkingVehicleRepository;

    @Transactional(readOnly = true)
    @Override
    public VehicleResponseDto.ParkingInfo findParkingVehicle(VehicleRequestDto.VehiclePlateRequest request) {

        ResidentVehicle residentVehicle = getResidentVehicle(request.getAptCode(), request.getVehicleNo());

        if (!ObjectUtils.isEmpty(residentVehicle)) {
            return VehicleResponseDto.ParkingInfo.builder()
                    .vehicleNo(residentVehicle.getVehicleNo())
                    .info(residentVehicle.getInfo())
                    .status(VehicleType.RESIDENT)
                    .build();
        }

        VisitorVehicle visitorVehicle = visitorVehicleRepository.findByAptCodeAndVehicleNoAndVisitDateLessThanEqualAndVisitCloseDateGreaterThanEqual(request.getAptCode(), request.getVehicleNo(), LocalDate.now(), LocalDate.now()).orElse(null);

        if (!ObjectUtils.isEmpty(visitorVehicle)) {
            return VehicleResponseDto.ParkingInfo.builder()
                    .vehicleNo(visitorVehicle.getVehicleNo())
                    .info(visitorVehicle.getInfo())
                    .status(VehicleType.VISITOR)
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
    public VehicleResponseDto.VehicleInfo saveResidentVehicle(VehicleRequestDto.RegistrationRequest request) {

        ResidentVehicle residentVehicle = residentVehicleRepository.findById(new ResidentVehicleId(request.getAptCode(), request.getVehicleNo())).orElse(null);

        if (residentVehicle != null) {
            return null;
        }

        ResidentVehicle savedResidentVehicle = residentVehicleRepository.save(ResidentVehicle.setData(request));
        return VehicleResponseDto.VehicleInfo.setData(savedResidentVehicle);
    }

    @Transactional
    @Override
    public VehicleResponseDto.VehicleInfo updateResidentVehicle(String vehicleNo, VehicleRequestDto.ModificationRequest request) {

        ResidentVehicle residentVehicle = residentVehicleRepository.findById(new ResidentVehicleId(request.getAptCode(), vehicleNo)).orElseThrow(() -> new NoSuchElementException("Resident vehicle not found"));

        residentVehicle.update(request);

        return VehicleResponseDto.VehicleInfo.setData(residentVehicle);
    }

    @Transactional
    @Override
    public VehicleResponseDto.VehicleInfo changeUsageResidentVehicle(VehicleRequestDto.VehiclePlateRequest request) {

        ResidentVehicle residentVehicle = residentVehicleRepository.findById(new ResidentVehicleId(request.getAptCode(), request.getVehicleNo())).orElseThrow(() -> new IllegalArgumentException("Resident vehicle not found"));
        residentVehicle.switchUsage();

        return VehicleResponseDto.VehicleInfo.setData(residentVehicle);
    }

    @Transactional
    @Override
    public void deleteResidentVehicle(VehicleRequestDto.VehiclePlateRequest request) {
        residentVehicleRepository.deleteById(new ResidentVehicleId(request.getAptCode(), request.getVehicleNo()));
    }

    @Transactional
    @Override
    public VisitorVehicleResponseDto.VisitorVehicleInfo saveVisitVehicle(VisitorVehicleRequestDto.RegistrationRequest registrationRequest) {

        Optional<VisitorVehicle> registeredVehicle = visitorVehicleRepository
                .findById(new VisitorVehicleId(registrationRequest.getAptCode(), registrationRequest.getVehicleNo(), registrationRequest.getVisitDate()));

        if (registeredVehicle.isPresent()) {
            return null;
        }

        VisitorVehicle savedVehicle = visitorVehicleRepository.save(VisitorVehicle.setData(registrationRequest));
        return VisitorVehicleResponseDto.VisitorVehicleInfo.setData(savedVehicle);
    }

    @Transactional
    @Override
    public VisitorVehicleResponseDto.VisitorVehicleInfo updateVisitVehicle(String vehicleNo, VisitorVehicleRequestDto.ModificationRequest modificationRequest) {
        VisitorVehicle visitorVehicle = visitorVehicleRepository.findById(new VisitorVehicleId(modificationRequest.getAptCode(), vehicleNo, modificationRequest.getVisitDate()))
                .orElseThrow(() -> new NoSuchElementException("Visitor vehicle not found"));

        visitorVehicle.update(modificationRequest);
        return VisitorVehicleResponseDto.VisitorVehicleInfo.setData(visitorVehicle);
    }

    @Transactional
    @Override
    public void deleteVisitorVehicle(RequestContext context, String vehicleNo, LocalDate visitDate) {
        visitorVehicleRepository.deleteById(new VisitorVehicleId(context.aptCode(), vehicleNo, visitDate));
    }

    @Transactional(readOnly = true)
    @Override
    public VehicleResponseDto.VehicleInfo getVehicle(VehicleRequestDto.VehiclePlateRequest request) {

        ResidentVehicle residentVehicle = getResidentVehicle(request.getAptCode(), request.getVehicleNo());

        if (!ObjectUtils.isEmpty(residentVehicle)) {
            return VehicleResponseDto.VehicleInfo.setData(residentVehicle);
        }

        VisitorVehicle visitorVehicle = getVisitorVehicle(request.getAptCode(), request.getVehicleNo());

        if (!ObjectUtils.isEmpty(visitorVehicle)) {
            return VehicleResponseDto.VehicleInfo.setData(visitorVehicle);
        }

        ParkingVehicle parkingVehicle = getParkingVehicle(request.getAptCode(), request.getVehicleNo());

        if (!ObjectUtils.isEmpty(parkingVehicle)) {
            return VehicleResponseDto.VehicleInfo.setData(parkingVehicle.getAptCode(), parkingVehicle.getVehicleNo());
        }

        return VehicleResponseDto.VehicleInfo.noData();
    }

    private ResidentVehicle getResidentVehicle(String aptCode, String vehicleNo) {
        return residentVehicleRepository.findById(new ResidentVehicleId(aptCode, vehicleNo)).orElse(null);
    }

    private VisitorVehicle getVisitorVehicle(String aptCode, String vehicleNo) {
        List<VisitorVehicle> VisitorVehicles = visitorVehicleRepository.findByAptCodeAndVehicleNo(aptCode, vehicleNo);

        if (!CollectionUtils.isEmpty(VisitorVehicles)) {
            return VisitorVehicles.get(0);
        }

        return null;
    }

    private ParkingVehicle getParkingVehicle(String aptCode, String vehicleNo) {
        List<ParkingVehicle> parkingVehicles = parkingVehicleRepository.findByAptCodeAndVehicleNo(aptCode, vehicleNo);

        if (!CollectionUtils.isEmpty(parkingVehicles)) {
            return parkingVehicles.get(0);
        }

        return null;
    }
}