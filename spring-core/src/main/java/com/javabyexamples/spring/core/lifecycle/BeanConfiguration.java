package com.javabyexamples.spring.core.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean(destroyMethod = "destroy", initMethod = "initialize")
    public CustomMethodsService withCustomMethodsService() {
        return new CustomMethodsService();
    }

    @Bean(destroyMethod = "customDestroy", initMethod = "customInit")
    public MixedService mixedService() {
        return new MixedService();
    }
}
