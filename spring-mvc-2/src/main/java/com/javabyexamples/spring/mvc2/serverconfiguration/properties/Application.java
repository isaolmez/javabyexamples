package com.javabyexamples.spring.mvc2.serverconfiguration.properties;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder().main(Application.class).profiles("serverproperties").run(args);
    }
}
