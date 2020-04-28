package com.javabyexamples.spring.core.componentindex.javaconfigbased;

import com.javabyexamples.spring.core.componentindex.DefaultPersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public DefaultPersonService anotherService() {
        return new DefaultPersonService();
    }

    @Bean
    public DefaultPersonService yetAnotherService() {
        return new DefaultPersonService();
    }

}
