package com.windsoft.apartment_parking_manager.service;

import com.windsoft.apartment_parking_manager.data.dto.ApartmentRequestDto;
import com.windsoft.apartment_parking_manager.data.entity.Apartment;

public interface ApartmentService {

    /**
     * 로그인 화면
     * 주소로 아파트 정보 조회
     * @param Address 주소
     * @return 아파트 정보
     */
    Apartment retrieveApartmentByAddress(String Address);

    /**
     * 아파트 정보 저장
     * @param requestDto 아파트 정보
     * @return 저장된 아파트 정보
     */
    Apartment saveApartment(ApartmentRequestDto.Registration requestDto);

    /**
     * 강제 아파트 정보 삭제
     * 경비원 저장 후 싪패 시 강제 삭제
     * @param aptCode 생성된 아파트 코드
     */
    void forceDeleteApartment(String aptCode);

    /**
     * 아파트 정보 삭제
     * 아파트에 연결된 정보 전체 삭제
     * @param aptCode 아파트 코드
     */
    void deleteApartmentofAll(String aptCode);
}
