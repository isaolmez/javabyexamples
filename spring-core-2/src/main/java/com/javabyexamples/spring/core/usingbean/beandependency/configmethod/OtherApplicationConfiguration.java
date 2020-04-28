package com.javabyexamples.spring.core.usingbean.beandependency.configmethod;

import com.javabyexamples.spring.core.usingbean.beandependency.GreetingProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OtherApplicationConfiguration {

    @Bean
    public GreetingProperties greetingProperties() {
        return new GreetingProperties();
    }
}
