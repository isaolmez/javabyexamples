package com.javabyexamples.spring.core.usingbean.beandependencies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public DefaultServiceOne serviceOne() {
        return new DefaultServiceOne(serviceProperties());
    }

    @Bean
    public DefaultServiceTwo serviceTwo() {
        return new DefaultServiceTwo(serviceProperties());
    }

    @Bean
    public DefaultServiceThree serviceThree(ServiceProperties serviceProperties) {
        return new DefaultServiceThree(serviceProperties);
    }

    @Bean
    public ServiceProperties serviceProperties() {
        return new ServiceProperties();
    }
}
