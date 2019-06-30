package com.javabyexamples.spring.mvc1.restwithjson;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

@Component
public class JacksonCustomizer implements Jackson2ObjectMapperBuilderCustomizer {

    // Jackson2ObjectMapperBuilder.customizeDefaultFeatures()

    @Override
    public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        jacksonObjectMapperBuilder.failOnEmptyBeans(false);
        jacksonObjectMapperBuilder.indentOutput(true);
    }
}
