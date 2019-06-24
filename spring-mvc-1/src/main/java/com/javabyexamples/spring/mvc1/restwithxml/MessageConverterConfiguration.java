package com.javabyexamples.spring.mvc1.restwithxml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;

@Configuration
public class MessageConverterConfiguration {

    @Bean
    public HttpMessageConverter xmlConverter() {
        MarshallingHttpMessageConverter marshallingHttpMessageConverter = new MarshallingHttpMessageConverter();
        marshallingHttpMessageConverter.setMarshaller();
    }
}
