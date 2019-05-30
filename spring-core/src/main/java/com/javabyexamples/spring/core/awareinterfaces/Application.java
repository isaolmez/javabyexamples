package com.javabyexamples.spring.core.awareinterfaces;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Application.class);
        applicationContext.getEnvironment().setActiveProfiles("test");
        applicationContext.refresh();

        applicationContext.close();
    }
}
