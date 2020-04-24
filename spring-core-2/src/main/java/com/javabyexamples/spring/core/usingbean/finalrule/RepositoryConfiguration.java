package com.javabyexamples.spring.core.usingbean.finalrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public /*final*/ class RepositoryConfiguration {

    @Bean
    /*private*/ public /*final*/ DefaultRepositoryOne defaultRepositoryOne() {
        return new DefaultRepositoryOne();
    }
}
