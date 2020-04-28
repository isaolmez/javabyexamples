package com.javabyexamples.spring.core.usingbean.beandependencies.configvariable;

import com.javabyexamples.spring.core.usingbean.beandependencies.DefaultGreetingService;
import com.javabyexamples.spring.core.usingbean.beandependencies.GreetingProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(OtherApplicationConfiguration.class)
public class ApplicationConfiguration {

    private final GreetingProperties greetingProperties;

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
