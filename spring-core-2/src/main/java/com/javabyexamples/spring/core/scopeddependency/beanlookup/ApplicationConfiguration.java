package com.javabyexamples.spring.core.scopeddependency.beanlookup;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ClientService clientService() {
        return new ClientService() {
            @Override
            protected Timer getTimer() {
                return timer();
            }
        };
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Timer timer() {
        return new Timer();
    }
}
