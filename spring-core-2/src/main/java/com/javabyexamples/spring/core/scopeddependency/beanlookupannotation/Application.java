package com.javabyexamples.spring.core.scopeddependency.beanlookupannotation;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(Application.class);

        final ClientService clientService = applicationContext.getBean(ClientService.class);
        clientService.doWork();
        clientService.doWork();
        clientService.doWork();

        clientService.doWorkWithName();
        clientService.doWorkWithName();
        clientService.doWorkWithName();

        applicationContext.close();
    }
}
