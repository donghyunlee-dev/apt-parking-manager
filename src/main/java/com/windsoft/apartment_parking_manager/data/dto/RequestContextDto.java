package com.windsoft.apartment_parking_manager.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class RequestContextDto {
    private String aptCode;
    private String bouncerCode;
    private String deviceId;
    private String appVersion;

    public void setContext(RequestContext context) {
        aptCode = context.aptCode();
        bouncerCode = context.bouncerCode();
        deviceId = context.deviceId();
        appVersion = context.appVersion();
    }
}
