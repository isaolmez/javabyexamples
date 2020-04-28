package com.javabyexamples.spring.core.initializecontext;

import com.javabyexamples.spring.core.initializecontext.service.DefaultGreetService;
import com.javabyexamples.spring.core.initializecontext.service.GreetService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        describe("Configurations In Constructor");
        withConfigurationsInConstructor();

        describe("Components In Constructor");
        withComponentsInConstructor();

        describe("Register Configurations Manually");
        withRegistration();

        describe("Scan Components Manually");
        withComponentScan();
    }

    private static void withConfigurationsInConstructor() {
        ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        final GreetService greetService = applicationContext.getBean(GreetService.class);
        greetService.greet();

        applicationContext.close();
    }

    private static void withComponentsInConstructor() {
        ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(DefaultGreetService.class);

        final GreetService greetService = applicationContext.getBean(GreetService.class);
        greetService.greet();

        applicationContext.close();
    }

    private static void withRegistration() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ApplicationConfiguration.class);
        applicationContext.refresh();

        final GreetService greetService = applicationContext.getBean(GreetService.class);
        greetService.greet();

        applicationContext.close();
    }

    private static void withComponentScan() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan(GreetService.class.getPackage().getName());
        applicationContext.refresh();

        final GreetService greetService = applicationContext.getBean(GreetService.class);
        greetService.greet();

        applicationContext.close();
    }

    private static void describe(String title) {
        System.out.println("#############################################");
        System.out.println("### " + title);
        System.out.println("#############################################");
    }
}
