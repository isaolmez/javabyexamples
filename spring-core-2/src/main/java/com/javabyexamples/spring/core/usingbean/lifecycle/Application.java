package com.javabyexamples.spring.core.usingbean.lifecycle;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(
          ServiceConfiguration.class);

        applicationContext.close();
    }
}
