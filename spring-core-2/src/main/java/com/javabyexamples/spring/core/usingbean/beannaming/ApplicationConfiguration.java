package com.javabyexamples.spring.core.usingbean.beannaming;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ApplicationConfiguration {

    @Bean
    public DefaultGreetingService greetingService() {
        return new DefaultGreetingService();
    }

    @Bean({"firstGreetingService", "theGreetingService"})
    public DefaultGreetingService serviceWithName() {
        return new DefaultGreetingService();
    }

    @Bean
    @Qualifier("qualifiedGreetingService")
    public DefaultGreetingService serviceWithQualifier() {
        return new DefaultGreetingService();
    }
}
