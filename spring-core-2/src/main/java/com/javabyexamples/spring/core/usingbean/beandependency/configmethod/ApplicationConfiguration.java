package com.javabyexamples.spring.core.usingbean.beandependency.configmethod;

import com.javabyexamples.spring.core.usingbean.beandependency.DefaultGreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(OtherApplicationConfiguration.class)
public class ApplicationConfiguration {

    private final OtherApplicationConfiguration otherApplicationConfiguration;

    public ApplicationConfiguration(OtherApplicationConfiguration otherApplicationConfiguration) {
        this.otherApplicationConfiguration = otherApplicationConfiguration;
    }

    @Bean
    public DefaultGreetingService defaultGreetingService() {
        return new DefaultGreetingService(otherApplicationConfiguration.greetingProperties());
    }
}
