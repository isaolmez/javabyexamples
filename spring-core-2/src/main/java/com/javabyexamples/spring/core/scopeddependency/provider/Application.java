package com.javabyexamples.spring.core.scopeddependency.provider;

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

        applicationContext.close();
    }
}
