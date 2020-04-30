package com.javabyexamples.spring.core.spel.beanusage;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(
            Application.class);

        applicationContext.close();
    }
}
