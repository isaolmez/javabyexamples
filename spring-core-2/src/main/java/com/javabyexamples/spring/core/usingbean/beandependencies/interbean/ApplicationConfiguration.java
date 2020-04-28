package com.javabyexamples.spring.core.usingbean.beandependencies.interbean;

import com.javabyexamples.spring.core.usingbean.beandependencies.DefaultGreetingService;
import com.javabyexamples.spring.core.usingbean.beandependencies.GreetingProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public DefaultGreetingService defaultGreetingService() {
        return new DefaultGreetingService(greetingProperties());
    }

    @Bean
    public GreetingProperties greetingProperties() {
        return new GreetingProperties();
    }
}
