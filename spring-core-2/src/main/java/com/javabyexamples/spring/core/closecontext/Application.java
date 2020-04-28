package com.javabyexamples.spring.core.closecontext;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        closeManually();
//        closeAutomatically();
//        closeAsCloseable();
    }

    private static void closeManually() {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(
          Application.class);

        // Additional logic...

        applicationContext.close();
    }

    private static void closeAutomatically() {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(
          Application.class);
        applicationContext.registerShutdownHook();

        // Additional logic...
    }

    private static void closeAsCloseable() {
        try (ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(
          Application.class)) {
            // Additional logic...
        }
    }
}
