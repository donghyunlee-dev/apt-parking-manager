package com.windsoft.apartment_parking_manager.service.impl;

import com.windsoft.apartment_parking_manager.data.dto.ApartmentRequestDto;
import com.windsoft.apartment_parking_manager.data.entity.Apartment;
import com.windsoft.apartment_parking_manager.data.entity.id.ApartmentId;
import com.windsoft.apartment_parking_manager.data.repository.ApartmentRepository;
import com.windsoft.apartment_parking_manager.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;

    @Override
    public Apartment retrieveApartmentByAddress(String address) {
        return apartmentRepository.findByAddressAndStatus(address, "ACTIVE");
    }

    @Transactional
    @Override
    public Apartment saveApartment(ApartmentRequestDto.Registration requestDto) {

        String maxApartmentCode = apartmentRepository.getMaxApartmentCode();
        StringBuilder newCode = new StringBuilder();
        newCode.append("A");

        try {
            int splitSequence = Integer.parseInt(maxApartmentCode.substring(1, maxApartmentCode.length()));
            newCode.append(String.format("%04d", ++splitSequence));
        } catch (NumberFormatException e) {
            newCode.append("0000");
            e.printStackTrace();
        }

        Apartment apartment = Apartment.newInstance(newCode.toString(), requestDto);

        return apartmentRepository.save(apartment);
    }

    @Transactional
    @Override
    public void forceDeleteApartment(String aptCode) {
        apartmentRepository.deleteById(new ApartmentId(aptCode));
    }

    @Transactional
    @Override
    public void deleteApartmentofAll(String aptCode) {

    }
}
