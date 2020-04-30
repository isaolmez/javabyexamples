package com.javabyexamples.spring.core.fileasresource;

import java.io.IOException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
          Application.class);

        applicationContext.getBean(AsDependencyGreetingService.class).greet();

        applicationContext.getBean(ResourceLoaderGreetingService.class).greet();

        applicationContext.getBean(ResourceGreetingService.class).greet();

        applicationContext.close();
    }
}
