package com.windsoft.apartment_parking_manager.controller;

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
    public ResponseEntity<?> searchApartmentInfo(@RequestParam String address) {

        Apartment apartment = apartmentService.retriveApartmentByAddress(address);
        return new ResponseEntity<>(apartment, HttpStatus.OK);
    }
}
