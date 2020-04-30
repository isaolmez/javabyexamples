package com.javabyexamples.spring.core.importannotation.metaannotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCounter
public class MainConfiguration {

    @Bean
    public ImpressionService impressionService(Counter counter) {
        return new ImpressionService(counter);
    }
}
