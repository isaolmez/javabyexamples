package com.javabyexamples.spring.mvc1.requestresponsebodyadvice;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Configuration
@Profile("manualRegistration")
public class CustomWebMvcConfiguration extends DelegatingWebMvcConfiguration {

    @Bean
    @Override
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = super.requestMappingHandlerAdapter();
        List<RequestBodyAdvice> requestBodyAdvices = new ArrayList<>();
        requestBodyAdvices.add(new CustomRequestBodyAdvice());
        requestMappingHandlerAdapter.setRequestBodyAdvice(requestBodyAdvices);

        List<ResponseBodyAdvice<?>> responseBodyAdvices = new ArrayList<>();
        responseBodyAdvices.add(new CustomResponseBodyAdvice());
        requestMappingHandlerAdapter.setResponseBodyAdvice(responseBodyAdvices);

        return requestMappingHandlerAdapter;
    }
}
