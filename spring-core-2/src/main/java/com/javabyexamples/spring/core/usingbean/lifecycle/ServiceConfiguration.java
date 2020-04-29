package com.javabyexamples.spring.core.usingbean.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean(initMethod = "init", destroyMethod = "stop")
    public DefaultPersonService defaultPersonService() {
        return new DefaultPersonService();
    }

    @Bean(destroyMethod = "stop")
    public DefaultPersonService personServiceManualInit() {
        final DefaultPersonService personService = new DefaultPersonService();
        personService.init();
        return personService;
    }
}
