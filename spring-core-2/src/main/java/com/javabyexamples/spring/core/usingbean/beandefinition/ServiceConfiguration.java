package com.javabyexamples.spring.core.usingbean.beandefinition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ServiceConfiguration {

    @Bean
    public ServiceOne serviceOne() {
        return new DefaultService();
    }

    @Bean
    public DefaultService defaultServiceOne() {
        return new DefaultService();
    }
}
