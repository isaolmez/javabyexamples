package com.javabyexamples.spring.core.initializecontainer;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContainerInitialization {

    public static void main(String[] args) {
        describe("Configuration In Constructor");
        withConfiguration();

        describe("Bean In Constructor");
        withBeans();

        describe("Register Configurations Manually");
        withRegistration();

        describe("Scan Components Manually");
        withComponentScan();
    }

    private static void withConfiguration() {
        ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(ServiceConfiguration.class);

        final GreetService greetService = applicationContext.getBean(GreetService.class);
        greetService.greet();

        applicationContext.close();
    }

    private static void withBeans() {
        ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(DefaultGreetService.class);

        final GreetService greetService = applicationContext.getBean(GreetService.class);
        greetService.greet();

        applicationContext.close();
    }

    private static void withRegistration() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ServiceConfiguration.class);
        applicationContext.refresh();

        final GreetService greetService = applicationContext.getBean(GreetService.class);
        greetService.greet();

        applicationContext.close();
    }

    private static void withComponentScan() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan(GreetService.class.getPackage().getName());
        System.out.println("Package:" + GreetService.class.getPackage().getName());
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
