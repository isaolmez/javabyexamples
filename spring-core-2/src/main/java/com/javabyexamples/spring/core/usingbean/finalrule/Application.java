package com.javabyexamples.spring.core.usingbean.finalrule;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(RepositoryConfiguration.class);

        applicationContext.getBean(RepositoryOne.class).save();

        applicationContext.close();
    }
}
