package com.windsoft.apartment_parking_manager.service;

import com.windsoft.apartment_parking_manager.data.dto.AccessRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.AccessResponseDto;
import com.windsoft.apartment_parking_manager.data.dto.AdminRequestDto;
import com.windsoft.apartment_parking_manager.data.dto.AdminResponseDto;

public interface AccessService {
    /**
     * 앱 인증
     * @param requestDto 인증 정보
     * @return 인증된 경비원 정보
     */
    AccessResponseDto validateAccess(AccessRequestDto requestDto);

    /**
     * 웹 로그인
     * @param requestDto 아파트명 검색, 관리자 핀번호
     * @return 관리자 정보
     */
    AdminResponseDto signIn(AdminRequestDto requestDto);
}
