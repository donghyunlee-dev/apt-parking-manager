package com.windsoft.apartment_parking_manager.controller;

import com.windsoft.apartment_parking_manager.data.dto.*;
import com.windsoft.apartment_parking_manager.data.entity.Apartment;
import com.windsoft.apartment_parking_manager.service.ApartmentService;
import com.windsoft.apartment_parking_manager.service.BouncerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/apartment")
@RestController
public class ApartmentController {

    private final ApartmentService apartmentService;
    private final BouncerService bouncerService;

    @PostMapping
    public ResponseEntity<BaseResponseDto<?>> registerApartment(@RequestBody ApartmentRequestDto.Registration requestDto) {

        Apartment apartment = apartmentService.saveApartment(requestDto);

        ApartmentResponseDto response;

        if (apartment == null) {
            response = ApartmentResponseDto.noData();
        } else {
            BouncerRequestDto.Registration adminBouncer = BouncerRequestDto.Registration
                    .builder()
                    .aptCode(apartment.getAptCode())
                    .finNo(requestDto.getFinNo())
                    .deviceId(requestDto.getDeviceId())
                    .build();

            BouncerResponseDto bouncerResponseDto = bouncerService.saveBouncer(adminBouncer);

            if (ObjectUtils.isEmpty(bouncerResponseDto)) {
                apartmentService.forceDeleteApartment(apartment.getAptCode());
                response = ApartmentResponseDto.noData();
            } else {
                response = ApartmentResponseDto.setData(apartment);
            }
        }

        return ResponseEntity.ok(new BaseResponseDto<>(response));
    }

    @GetMapping
    public ResponseEntity<?> searchApartmentInfo(@ModelAttribute ApartmentRequestDto.Verification request) {
        Apartment apartment = apartmentService.retrieveApartmentByAddress(request.getAddress());

        ApartmentResponseDto response;

        if (apartment == null) {
            response = ApartmentResponseDto.noData();
        } else {
            response = ApartmentResponseDto.setData(apartment);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
