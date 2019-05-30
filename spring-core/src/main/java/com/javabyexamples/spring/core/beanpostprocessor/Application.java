package com.javabyexamples.spring.core.beanpostprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan
public class Application {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
          Application.class);

        GreetingService greetingService = applicationContext.getBean("englishGreetingService", GreetingService.class);
        greetingService.hello();

        applicationContext.close();
    }
}
