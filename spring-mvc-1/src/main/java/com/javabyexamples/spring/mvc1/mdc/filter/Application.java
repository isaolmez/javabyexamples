package com.javabyexamples.spring.mvc1.mdc.filter;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder().main(Application.class).profiles("mdc").run(args);
    }
}
