package com.windsoft.apartment_parking_manager.data.dto;

import com.windsoft.apartment_parking_manager.data.entity.Bouncer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AccessResponseDto {

    private String bouncerCode;

    private String bouncerName;

    private String grade;

    public static AccessResponseDto noData() {
        AccessResponseDto emptyResponse = new AccessResponseDto();
        emptyResponse.bouncerCode = "";
        emptyResponse.bouncerName = "";
        emptyResponse.grade = "";
        return emptyResponse;
    }

    public static AccessResponseDto setData(Bouncer bouncer) {
        AccessResponseDto emptyResponse = new AccessResponseDto();
        emptyResponse.bouncerCode = bouncer.getBouncerCode();
        emptyResponse.bouncerName = bouncer.getBouncerName();
        emptyResponse.grade = bouncer.getGrade();
        return emptyResponse;
    }
}
