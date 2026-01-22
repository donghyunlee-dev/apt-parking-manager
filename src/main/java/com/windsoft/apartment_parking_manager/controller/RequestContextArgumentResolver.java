package com.windsoft.apartment_parking_manager.controller;

import com.windsoft.apartment_parking_manager.data.dto.RequestContext;
import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class RequestContextArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String HEADER_APARTMENT_CODE = "X-Apt-Code";
    private static final String HEADER_BOUNCER_CODE = "X-Bouncer-Code";
    private static final String HEADER_DEVICE_ID = "X-Device-Id";
    private static final String HEADER_APP_VERSION = "X-App-Version";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(RequestContext.class);
    }

    @Override
    public @Nullable Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer, NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {
        String aptCode = webRequest.getHeader(HEADER_APARTMENT_CODE);
        String boucode = webRequest.getHeader(HEADER_BOUNCER_CODE);
        String deviceId = webRequest.getHeader(HEADER_DEVICE_ID);
        String appVersion = webRequest.getHeader(HEADER_APP_VERSION);

        if (!StringUtils.hasText(aptCode)) {
            throw new MissingRequestHeaderException(HEADER_APARTMENT_CODE, parameter);
        }

        if (!StringUtils.hasText(boucode)) {
            throw new MissingRequestHeaderException(HEADER_BOUNCER_CODE, parameter);
        }

        if (!StringUtils.hasText(deviceId)) {
            throw new MissingRequestHeaderException(HEADER_DEVICE_ID, parameter);
        }

        if (!StringUtils.hasText(appVersion)) {
            throw new MissingRequestHeaderException(HEADER_APP_VERSION, parameter);
        }

        return new RequestContext(aptCode, boucode, deviceId, appVersion);
    }
}
