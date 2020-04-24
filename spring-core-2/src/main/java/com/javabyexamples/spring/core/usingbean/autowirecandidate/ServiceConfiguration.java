package com.javabyexamples.spring.core.usingbean.autowirecandidate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ServiceConfiguration {

    @Bean
    public DefaultPersonRepository defaultPersonRepository() {
        return new DefaultPersonRepository();
    }

    @Bean(autowireCandidate = false)
    public MockPersonRepository mockPersonRepository() {
        return new MockPersonRepository();
    }
}
