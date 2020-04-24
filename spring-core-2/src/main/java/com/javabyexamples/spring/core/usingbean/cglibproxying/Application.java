package com.javabyexamples.spring.core.usingbean.cglibproxying;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        applicationContext.getBean(PostRepository.class).save();

        applicationContext.close();
    }
}
