package com.windsoft.apartment_parking_manager;

import com.windsoft.apartment_parking_manager.controller.RequestContextArgumentResolver;
import com.windsoft.apartment_parking_manager.data.dto.RequestContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
public class TestConfig {

    @Bean
    public RequestContextArgumentResolver requestContextArgumentResolver() throws Exception {
        RequestContextArgumentResolver mockResolver = mock(RequestContextArgumentResolver.class);

        // Configure the mock resolver to support RequestContext parameters
        when(mockResolver.supportsParameter(org.mockito.ArgumentMatchers.any(MethodParameter.class))).thenReturn(true);

        // Configure the mock resolver to return a default RequestContext
        // This RequestContext will be used during test context loading
        when(mockResolver.resolveArgument(
                org.mockito.ArgumentMatchers.any(MethodParameter.class),
                org.mockito.ArgumentMatchers.any(ModelAndViewContainer.class),
                org.mockito.ArgumentMatchers.any(NativeWebRequest.class),
                org.mockito.ArgumentMatchers.any(WebDataBinderFactory.class)))
                .thenReturn(new RequestContext("testAptCode", "testBouncerCode", "testDeviceId", "testAppVersion"));

        return mockResolver;
    }
}
