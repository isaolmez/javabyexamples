package com.javabyexamples.spring.mvc1.mdc.interceptor;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder().main(com.javabyexamples.spring.mvc1.mdc.filter.Application.class).profiles("mdc").run(args);
    }
}
