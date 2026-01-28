package com.windsoft.apartment_parking_manager.service.impl;

import com.windsoft.apartment_parking_manager.data.entity.Apartment;
import com.windsoft.apartment_parking_manager.data.repository.ApartmentRepository;
import com.windsoft.apartment_parking_manager.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;

    @Override
    public Apartment retriveApartmentByAddress(String address) {
        return apartmentRepository.findByAddressAndStatus(address, "ACTIVE");
    }

    public Apartment registerApartment(Apartment apartment) {

        String newApartmentCode;

        List<Apartment> list = findApartments();

        if (ObjectUtils.isEmpty(list)) {
            newApartmentCode =  "A0001";
        }

        newApartmentCode = makeMaxApartmentCode(list.getFirst());

        return apartmentRepository.save(apartment);
    }

    private String makeMaxApartmentCode(Apartment recentApartment) {
        Integer maxCode = Integer.valueOf(recentApartment.getAptCode().substring(1, recentApartment.getAptCode().length() - 1));

        if (ObjectUtils.isEmpty(maxCode)) {
            maxCode = 1;
        }
        return "A" + String.format("%04d", maxCode);
    }

    private List<Apartment> findApartments() {
        return apartmentRepository.findAll().stream().sorted(Comparator.comparing(Apartment::getAptCode).reversed()).toList();
    }
}
