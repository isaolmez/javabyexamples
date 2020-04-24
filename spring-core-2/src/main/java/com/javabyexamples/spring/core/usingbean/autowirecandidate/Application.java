package com.javabyexamples.spring.core.usingbean.autowirecandidate;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(ServiceConfiguration.class);

        applicationContext.getBean(PersonService.class).perform();

        applicationContext.close();
    }
}
