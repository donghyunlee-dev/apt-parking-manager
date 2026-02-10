package com.windsoft.apartment_parking_manager.service;

import com.windsoft.apartment_parking_manager.data.dto.ApartmentRequestDto;
import com.windsoft.apartment_parking_manager.data.entity.Apartment;

public interface ApartmentService {

    Apartment retrieveApartmentByAddress(String Address);

    Apartment createApartment(ApartmentRequestDto.Registration requestDto);
}
