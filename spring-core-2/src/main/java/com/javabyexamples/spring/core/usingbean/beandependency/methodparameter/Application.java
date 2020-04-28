package com.javabyexamples.spring.core.usingbean.beandependency.methodparameter;

import com.javabyexamples.spring.core.usingbean.beandependency.GreetingService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        applicationContext.getBean(GreetingService.class).greet();

        applicationContext.close();
    }
}
