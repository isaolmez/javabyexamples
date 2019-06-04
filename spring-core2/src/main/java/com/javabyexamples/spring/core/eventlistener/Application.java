package com.javabyexamples.spring.core.eventlistener;

import com.javabyexamples.spring.core.componentscan.filter.AspectJIncludeConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(AspectJIncludeConfiguration.class);

        applicationContext.close();
    }
}
