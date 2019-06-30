package com.javabyexamples.spring.mvc1.jacksonconfiguration.properties;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder().main(Application.class).profiles("jacksonconfigure").run(args);
    }
}
