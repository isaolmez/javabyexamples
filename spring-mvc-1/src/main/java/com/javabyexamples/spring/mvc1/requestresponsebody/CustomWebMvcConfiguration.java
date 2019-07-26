package com.javabyexamples.spring.mvc1.requestresponsebody;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
@Profile("manualRegistration")
public class CustomWebMvcConfiguration extends DelegatingWebMvcConfiguration {

    @Bean
    @Override
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = super.requestMappingHandlerAdapter();
        List<RequestBodyAdvice> additionalAdvices = new ArrayList<>();
        additionalAdvices.add(new CustomRequestBodyAdvice());
        requestMappingHandlerAdapter.setRequestBodyAdvice(additionalAdvices);
        return requestMappingHandlerAdapter;
    }
}
