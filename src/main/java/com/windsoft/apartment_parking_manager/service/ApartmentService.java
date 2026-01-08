package com.windsoft.apartment_parking_manager.service;

import com.windsoft.apartment_parking_manager.data.entity.Apartment;

public interface ApartmentService {

    Apartment retriveApartmentByAddress(String Address);
}
