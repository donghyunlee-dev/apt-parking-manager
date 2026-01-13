package com.windsoft.apartment_parking_manager.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AccessResponseDto {

    private String bouncerCode;

    private String bouncerName;

    private String result;

    public static AccessResponseDto noData() {
        AccessResponseDto emptyResponse = new AccessResponseDto();
        emptyResponse.bouncerCode = "";
        emptyResponse.bouncerName = "";
        emptyResponse.result = "failed";
        return emptyResponse;
    }
}
