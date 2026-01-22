package com.windsoft.apartment_parking_manager.data.dto;

public record RequestContext(
    String aptCode,
    String bouncerCode,
    String deviceId,
    String appVersion
) {}
