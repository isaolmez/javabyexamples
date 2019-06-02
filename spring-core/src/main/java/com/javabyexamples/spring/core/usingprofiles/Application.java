package com.javabyexamples.spring.core.usingprofiles;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan
public class Application {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        withProd();
    }

    private static void withProd() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Application.class);
        applicationContext.getEnvironment().setActiveProfiles("prod");
        applicationContext.refresh();

        Map<String, EmployeeService> services = applicationContext.getBeansOfType(EmployeeService.class);
        System.out.println(services.keySet());

        applicationContext.close();
    }

    private static void withProdAndFast() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Application.class);
        applicationContext.getEnvironment().setDefaultProfiles("fast");
        applicationContext.getEnvironment().setActiveProfiles("prod", "fast");
        applicationContext.refresh();

        Map<String, EmployeeService> services = applicationContext.getBeansOfType(EmployeeService.class);
        System.out.println(services.keySet());

        applicationContext.close();
    }

    private static void withProd_FastAsDefault() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Application.class);
        applicationContext.getEnvironment().setDefaultProfiles("fast");
        applicationContext.getEnvironment().setActiveProfiles("prod");
        applicationContext.refresh();

        Map<String, EmployeeService> services = applicationContext.getBeansOfType(EmployeeService.class);
        System.out.println(services.keySet());

        applicationContext.close();
    }

    private static void withNoProfie() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Application.class);
        applicationContext.refresh();

        Map<String, EmployeeService> services = applicationContext.getBeansOfType(EmployeeService.class);
        System.out.println(services.keySet());

        applicationContext.close();
    }
}
