package com.javabyexamples.spring.core.scopeddependency.beanlookup;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        final ClientService clientService = applicationContext.getBean(ClientService.class);
        clientService.doWork();
        clientService.doWork();
        clientService.doWork();

        applicationContext.close();
    }
}
