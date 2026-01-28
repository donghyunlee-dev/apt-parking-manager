package com.windsoft.apartment_parking_manager.data.dto;

public record RequestContext(
    String aptCode,
    String bouncerCode,
    String deviceId,
    String appVersion
) {
    @Override
    public String toString() {
        return "RequestContext{" +
                "aptCode='" + aptCode + '\'' +
                ", bouncerCode='" + bouncerCode + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", appVersion='" + appVersion + '\'' +
                '}';
    }
}
