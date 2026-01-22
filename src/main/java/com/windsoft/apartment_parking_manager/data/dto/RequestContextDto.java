package com.windsoft.apartment_parking_manager.data.dto;

import lombok.Getter;

@Getter
public class RequestContextDto {
    private String aptCode;
    private String bouncerCode;
    private String deviceId;
    private String appVersion;

    public RequestContextDto(RequestContext context) {
        aptCode = context.aptCode();
        bouncerCode = context.bouncerCode();
        deviceId = context.deviceId();
        appVersion = context.appVersion();
    }
}
