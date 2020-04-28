package com.javabyexamples.spring.core.usingbean.beandependency.interbean;

import com.javabyexamples.spring.core.usingbean.beandependency.DefaultGreetingService;
import com.javabyexamples.spring.core.usingbean.beandependency.GreetingProperties;
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
