package com.javabyexamples.spring.core.importannotation.selector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CounterImportSelector.class)
public class MainConfiguration {

    @Bean
    public ImpressionService impressionService(Counter counter) {
        return new ImpressionService(counter);
    }
}
