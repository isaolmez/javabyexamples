package com.javabyexamples.spring.core.customqualifier;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);

        PersonServiceClient personServiceClient = applicationContext.getBean(PersonServiceClient.class);
        personServiceClient.start();

        applicationContext.close();
    }
}
