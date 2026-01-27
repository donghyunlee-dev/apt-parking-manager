package com.windsoft.apartment_parking_manager.service.impl;

import com.windsoft.apartment_parking_manager.data.dto.RequestContext;
import com.windsoft.apartment_parking_manager.data.dto.VisitVehicleDto;
import com.windsoft.apartment_parking_manager.data.entity.VisitVehicle;
import com.windsoft.apartment_parking_manager.data.repository.VisitVehicleRepository;
import com.windsoft.apartment_parking_manager.service.VisitVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class VisitVehicleServiceImpl implements VisitVehicleService {

    private final VisitVehicleRepository visitVehicleRepository;

    @Override
    public VisitVehicleDto.VisitVehicleInfo saveVisitVehicle(VisitVehicleDto.RegistrationRequest registrationRequest) {
        visitVehicleRepository.findByAptCodeAndVehicleNo(registrationRequest.getContext().aptCode(), registrationRequest.getVehicleNo())
                .ifPresent(v -> {
                    throw new IllegalStateException("Visit vehicle already exists");
                });

        VisitVehicle visitVehicle = new VisitVehicle();
        visitVehicle.update(registrationRequest);
        VisitVehicle savedVehicle = visitVehicleRepository.save(visitVehicle);
        return new VisitVehicleDto.VisitVehicleInfo(savedVehicle);
    }

    @Override
    public VisitVehicleDto.VisitVehicleInfo updateVisitVehicle(String vehicleNo, VisitVehicleDto.ModificationRequest modificationRequest) {
        VisitVehicle visitVehicle = visitVehicleRepository.findByAptCodeAndVehicleNo(modificationRequest.getContext().aptCode(), vehicleNo)
                .orElseThrow(() -> new NoSuchElementException("Visit vehicle not found"));

        visitVehicle.update(modificationRequest);
        VisitVehicle updatedVehicle = visitVehicleRepository.save(visitVehicle);
        return new VisitVehicleDto.VisitVehicleInfo(updatedVehicle);

    }

    @Override
    public void deleteVisitVehicle(RequestContext context, String vehicleNo) {
        VisitVehicle visitVehicle = visitVehicleRepository.findByAptCodeAndVehicleNo(context.aptCode(), vehicleNo)
                .orElseThrow(() -> new NoSuchElementException("Visit vehicle not found"));

        visitVehicleRepository.delete(visitVehicle);
    }
}
