package com.windsoft.apartment_parking_manager.controller;

import com.windsoft.apartment_parking_manager.data.dto.ApartmentRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.ApartmentResponseDto;
import com.windsoft.apartment_parking_manager.data.entity.Apartment;
import com.windsoft.apartment_parking_manager.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/apartment")
@RestController
public class ApartmentController {

    private final ApartmentService apartmentService;

    @PostMapping
    public ResponseEntity<?> registerApartment(@RequestBody ApartmentRequestDto.Registration requestDto) {
        Apartment apartment = apartmentService.createApartment(requestDto);

        ApartmentResponseDto response;

        if (apartment == null) {
            response = ApartmentResponseDto.noData();
        } else {
            response = ApartmentResponseDto.setData(apartment);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
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
