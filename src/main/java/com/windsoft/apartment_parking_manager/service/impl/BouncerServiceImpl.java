package com.windsoft.apartment_parking_manager.service.impl;

import com.windsoft.apartment_parking_manager.data.dto.BouncerRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.BouncerResponseDto;
import com.windsoft.apartment_parking_manager.data.entity.Bouncer;
import com.windsoft.apartment_parking_manager.data.repository.BouncerRepository;
import com.windsoft.apartment_parking_manager.service.BouncerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BouncerServiceImpl implements BouncerService {

    private final BouncerRepository bouncerRepository;

    @Transactional
    @Override
    public BouncerResponseDto saveBouncer(BouncerRequestDto.Registration addBouncerDto) {

        Bouncer bouncer = Bouncer.toEntity(addBouncerDto);
        Bouncer savedBouncer = bouncerRepository.save(bouncer);

        return BouncerResponseDto.toDto(savedBouncer);
    }
}
