package com.javabyexamples.spring.core.usingbean.beannaming;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(ServiceConfiguration.class);

        final GreetingClient greetingClient = applicationContext.getBean(GreetingClient.class);

        applicationContext.close();
    }
}
