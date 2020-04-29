package com.javabyexamples.spring.core.usingbean.beandependency.configvariable;

import com.javabyexamples.spring.core.usingbean.beandependency.DefaultGreetingService;
import com.javabyexamples.spring.core.usingbean.beandependency.GreetingProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(OtherApplicationConfiguration.class)
public class ApplicationConfiguration {

    private final GreetingProperties greetingProperties;

    @Autowired
    public ApplicationConfiguration(GreetingProperties greetingProperties) {
        this.greetingProperties = greetingProperties;
    }

    @Bean
    public DefaultGreetingService defaultGreetingService() {
        return new DefaultGreetingService(greetingProperties);
    }

    @Bean
    public DefaultGreetingService anotherGreetingService() {
        return new DefaultGreetingService(greetingProperties);
    }
}
