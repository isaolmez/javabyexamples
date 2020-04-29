package com.javabyexamples.spring.core.scopeddependency.beanlookup;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan
public class ApplicationConfiguration {

    @Bean
    public ClientService clientService() {
        return new ClientService() {
            @Override
            protected Counter getCounter() {
                return counter();
            }
        };
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Counter counter() {
        return new Counter();
    }
}
