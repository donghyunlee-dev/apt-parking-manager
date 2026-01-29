package com.windsoft.apartment_parking_manager.configuration;

import com.windsoft.apartment_parking_manager.controller.RequestContextArgumentResolver;
import com.windsoft.apartment_parking_manager.controller.SnakeCaseMedelAttributeResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RequestContextArgumentResolver requestContextResolver;
    private final SnakeCaseMedelAttributeResolver snakeCaseMedelAttributeResolver;

    public WebConfig(RequestContextArgumentResolver requestContextResolver, SnakeCaseMedelAttributeResolver snakeCaseMedelAttributeResolver) {
        this.requestContextResolver = requestContextResolver;
        this.snakeCaseMedelAttributeResolver = snakeCaseMedelAttributeResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(requestContextResolver);
        resolvers.add(snakeCaseMedelAttributeResolver);
    }
}
