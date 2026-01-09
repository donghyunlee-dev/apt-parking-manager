package com.windsoft.apartment_parking_manager.service.impl;

import com.windsoft.apartment_parking_manager.data.entity.Apartment;
import com.windsoft.apartment_parking_manager.data.repository.ApartmentRepository;
import com.windsoft.apartment_parking_manager.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;

    @Override
    public Apartment retriveApartmentByAddress(String address) {
        return apartmentRepository.findByAddressAndStatus(address, "ACTIVE");
    }
}
