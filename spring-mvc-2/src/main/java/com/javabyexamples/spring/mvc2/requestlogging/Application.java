package com.javabyexamples.spring.mvc2.requestlogging;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder().main(Application.class).profiles("requestlogging").run(args);
    }
}
