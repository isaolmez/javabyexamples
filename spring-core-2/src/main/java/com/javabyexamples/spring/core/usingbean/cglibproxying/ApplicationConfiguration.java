package com.javabyexamples.spring.core.usingbean.cglibproxying;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = true)
public /*final*/ class ApplicationConfiguration {

    @Bean
    /*private*/ public /*final*/ DefaultPostRepository postRepository() {
        return new DefaultPostRepository();
    }

    @Bean
    public DefaultPostService firstPostService() {
        return new DefaultPostService("First", postRepository(), logService());
    }

    @Bean
    public DefaultPostService secondPostService() {
        return new DefaultPostService("Second", postRepository(), logService());
    }

    @Bean
    public static LogService logService() {
        return new LogService();
    }
}
