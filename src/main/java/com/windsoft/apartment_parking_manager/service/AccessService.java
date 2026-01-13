package com.windsoft.apartment_parking_manager.service;

import com.windsoft.apartment_parking_manager.data.dto.AccessRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.AccessResponseDto;

public interface AccessService {
    AccessResponseDto validateAccess(AccessRequestDto requestDto);
}
