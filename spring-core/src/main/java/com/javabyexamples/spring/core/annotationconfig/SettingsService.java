package com.javabyexamples.spring.core.annotationconfig;

import org.springframework.stereotype.Component;

@Component
public class SettingsService {

    public void configure() {
        System.out.println("Configuring chat");
    }
}
