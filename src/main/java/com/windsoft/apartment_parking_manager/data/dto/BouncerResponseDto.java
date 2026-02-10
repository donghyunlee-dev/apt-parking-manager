package com.windsoft.apartment_parking_manager.data.dto;

import com.windsoft.apartment_parking_manager.data.entity.Bouncer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class BouncerResponseDto {

    private String aptCode;

    private String bouncerCode;

    private String bouncerName;

    private String grade;

    private String used;

    public static BouncerResponseDto toDto(Bouncer bouncer) {
        BouncerResponseDto responseDto = new BouncerResponseDto();
        responseDto.aptCode = bouncer.getAptCode();
        responseDto.bouncerCode = bouncer.getBouncerCode();
        responseDto.bouncerName = bouncer.getBouncerName();
        responseDto.grade = bouncer.getGrade();
        responseDto.used = bouncer.getUsed();
        return responseDto;
    }
}
