package com.javabyexamples.spring.core.disambiguatingbeans;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);

        WithoutQualifierArithmeticOperations withoutQualifier = applicationContext
          .getBean(WithoutQualifierArithmeticOperations.class);
        withoutQualifier.start();

        WithQualifierArithmeticOperations withQualifier = applicationContext
          .getBean(WithQualifierArithmeticOperations.class);
        withQualifier.start();

        applicationContext.close();
    }
}
