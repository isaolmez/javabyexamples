package com.javabyexamples.spring.core.usingbean.incomponent;

import org.springframework.context.annotation.Bean;

public class DefaultSampleService implements SampleService {

    @Override
    public void perform() {
        System.out.println("Performing...");
    }

    @Bean
    public DefaultSampleRepository sampleRepository() {
        return new DefaultSampleRepository();
    }
}
