package com.javabyexamples.spring.core.importannotation.selector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalCounterConfiguration {

    @Bean
    public Counter counter() {
        return new Counter("local");
    }
}
