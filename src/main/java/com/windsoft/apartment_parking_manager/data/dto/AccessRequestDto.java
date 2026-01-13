package com.windsoft.apartment_parking_manager.data.dto;

import lombok.Getter;

@Getter
public class AccessRequestDto {
    //아파트 코드
    private String aptCode;
    // fin 코드 6자리
    private String finCode;
    // 디바이스 id
    private String deviceId;
    // 위치, 주소 정보
    private String location;
}
