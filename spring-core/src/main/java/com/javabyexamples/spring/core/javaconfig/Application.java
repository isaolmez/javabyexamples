package com.javabyexamples.spring.core.javaconfig;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(
          SchoolConfiguration.class);

        SchoolService schoolService = applicationContext.getBean(SchoolService.class);
        schoolService.teachStudents();

        applicationContext.close();
    }
}
