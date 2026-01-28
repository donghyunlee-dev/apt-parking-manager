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

    @GetMapping
    public ResponseEntity<?> searchApartmentInfo(@ModelAttribute ApartmentRequestDto.verify request) {
        Apartment apartment = apartmentService.retriveApartmentByAddress(request.getAddress());

        ApartmentResponseDto response;

        if (apartment == null) {
            response = ApartmentResponseDto.noData();
        } else {
            response = ApartmentResponseDto.setData(apartment);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
