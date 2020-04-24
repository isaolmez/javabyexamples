package com.javabyexamples.spring.core.usingbean.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean(initMethod = "init", destroyMethod = "stop")
    public DefaultServiceOne serviceOne() {
        return new DefaultServiceOne();
    }

    @Bean(destroyMethod = "stop")
    public DefaultServiceOne serviceOneManualInit() {
        final DefaultServiceOne serviceOne = new DefaultServiceOne();
        serviceOne.init();
        return serviceOne;
    }
}
