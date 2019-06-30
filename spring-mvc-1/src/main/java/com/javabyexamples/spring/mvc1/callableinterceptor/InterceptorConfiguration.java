package com.javabyexamples.spring.mvc1.callableinterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private final HandlerInterceptor asyncHandlerInterceptor;

    @Autowired
    public InterceptorConfiguration(HandlerInterceptor asyncHandlerInterceptor) {
        this.asyncHandlerInterceptor = asyncHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(asyncHandlerInterceptor);
    }
}
