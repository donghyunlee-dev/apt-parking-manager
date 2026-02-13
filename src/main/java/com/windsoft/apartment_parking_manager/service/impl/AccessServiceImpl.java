package com.windsoft.apartment_parking_manager.service.impl;

import com.windsoft.apartment_parking_manager.data.dto.AccessRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.AccessResponseDto;
import com.windsoft.apartment_parking_manager.data.dto.AdminRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.AdminResponseDto;
import com.windsoft.apartment_parking_manager.data.entity.AccessLog;
import com.windsoft.apartment_parking_manager.data.entity.Apartment;
import com.windsoft.apartment_parking_manager.data.entity.Bouncer;
import com.windsoft.apartment_parking_manager.data.repository.AccessLogRepository;
import com.windsoft.apartment_parking_manager.data.repository.ApartmentRepository;
import com.windsoft.apartment_parking_manager.data.repository.BouncerRepository;
import com.windsoft.apartment_parking_manager.service.AccessService;
import com.windsoft.apartment_parking_manager.util.BcryptUtils;
import com.windsoft.apartment_parking_manager.util.CustomObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccessServiceImpl implements AccessService {

    private final AccessLogRepository accessLogRepository;
    private final ApartmentRepository apartmentRepository;
    private final BouncerRepository bouncerRepository;

    @Override
    public AccessResponseDto validateAccess(AccessRequestDto requestDto) {

        List<Bouncer> bouncers = getBouncers(requestDto.getAptCode());

        if (bouncers.isEmpty()) {
            return AccessResponseDto.noData();
        }

        Bouncer findBouncer = null;

        for (Bouncer bouncer : bouncers) {
            if (BcryptUtils.isValidPassword(requestDto.getFinCode(), bouncer.getFinNo())) {
                findBouncer = CustomObjectUtils.deepCopy(bouncer, Bouncer.class);
            }
        }

        if (ObjectUtils.isEmpty(findBouncer)) {
            return AccessResponseDto.noData();
        }
        AccessLog accessLog = AccessLog.toEntity(requestDto, findBouncer);
        accessLogRepository.save(accessLog);
        return AccessResponseDto.setData(findBouncer);
    }

    @Override
    public AdminResponseDto signIn(AdminRequestDto requestDto) {

        Apartment aptInfo = apartmentRepository.findByAptNameAndStatus(requestDto.getAptName(), "Y");

        if (ObjectUtils.isEmpty(aptInfo)) {
            return AdminResponseDto.builder().result("NOT_APT_FOUND").build();
        }

        List<Bouncer> bouncers = getBouncers(aptInfo.getAptCode());

        Bouncer adminAccount = null;

        for (Bouncer bouncer : bouncers) {
            if (bouncer.getGrade().equals("A") && BcryptUtils.isValidPassword(requestDto.getFinCode(), bouncer.getFinNo())) {
                adminAccount = CustomObjectUtils.deepCopy(bouncer, Bouncer.class);
                break;
            }
        }

        if (ObjectUtils.isEmpty(adminAccount)) {
            return AdminResponseDto.builder()
                    .aptCode(aptInfo.getAptCode())
                    .aptName(aptInfo.getAptName())
                    .address(aptInfo.getAddress())
                    .result("UNAUTHORIZED")
                    .build();
        }

        return AdminResponseDto.setData(aptInfo, adminAccount);
    }

    private List<Bouncer> getBouncers(String aptCode) {
        List<Bouncer> bouncers = bouncerRepository.findByAptCodeAndUsed(aptCode, "Y");
        return bouncers;
    }
}
