package com.windsoft.apartment_parking_manager.service;

import com.windsoft.apartment_parking_manager.data.dto.BouncerRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.BouncerResponseDto;

public interface BouncerService {

    BouncerResponseDto saveBouncer(final BouncerRequestDto.Registration addBouncerDto);
}
