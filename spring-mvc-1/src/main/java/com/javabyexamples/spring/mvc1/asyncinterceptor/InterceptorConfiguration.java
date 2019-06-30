package com.javabyexamples.spring.mvc1.asyncinterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private final LoggerAsyncHandlerInterceptor asyncHandlerInterceptor;

    @Autowired
    public InterceptorConfiguration(LoggerAsyncHandlerInterceptor asyncHandlerInterceptor) {
        this.asyncHandlerInterceptor = asyncHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(asyncHandlerInterceptor);
    }
}
