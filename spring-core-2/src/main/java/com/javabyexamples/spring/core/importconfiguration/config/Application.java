package com.javabyexamples.spring.core.importconfiguration.config;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(MainConfiguration.class);

        final Counter counter = applicationContext.getBean(Counter.class);
        counter.count();
        counter.count();
        counter.count();

        applicationContext.close();
    }
}
