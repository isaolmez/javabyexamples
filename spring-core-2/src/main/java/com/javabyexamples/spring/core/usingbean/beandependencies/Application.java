package com.javabyexamples.spring.core.usingbean.beandependencies;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(ServiceConfiguration.class);

        applicationContext.getBean(ServiceOne.class).one();
        applicationContext.getBean(ServiceTwo.class).two();
        applicationContext.getBean(ServiceThree.class).three();

        applicationContext.close();
    }
}
