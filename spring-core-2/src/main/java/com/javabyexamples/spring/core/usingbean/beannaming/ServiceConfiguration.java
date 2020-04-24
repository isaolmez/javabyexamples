package com.javabyexamples.spring.core.usingbean.beannaming;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ServiceConfiguration {

    @Bean
    public DefaultServiceOne serviceOne() {
        return new DefaultServiceOne();
    }

    @Bean({"firstService", "onlyService"})
    public DefaultServiceOne serviceOneWithName() {
        return new DefaultServiceOne();
    }

    @Bean
    @Qualifier("qualifiedService")
    public DefaultServiceOne serviceOneWithQualifier() {
        return new DefaultServiceOne();
    }
}
