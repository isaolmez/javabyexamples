package com.javabyexamples.spring.mvc1.jacksonconfiguration.explicit;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

@Component
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder
          .json()
          .failOnUnknownProperties(false)
          .failOnEmptyBeans(false)
          .serializationInclusion(Include.NON_NULL)
          .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
          .defaultViewInclusion(false)
          .build();
    }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return Jackson2ObjectMapperBuilder
          .json()
          .failOnUnknownProperties(false)
          .failOnEmptyBeans(false)
          .serializationInclusion(Include.NON_NULL)
          .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
          .defaultViewInclusion(false);
    }
}
