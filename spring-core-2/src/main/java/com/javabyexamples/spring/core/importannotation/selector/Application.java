package com.javabyexamples.spring.core.importannotation.selector;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(MainConfiguration.class);

        final ImpressionService impressionService = applicationContext.getBean(ImpressionService.class);
        impressionService.countImpression();
        impressionService.countImpression();
        impressionService.countImpression();

        applicationContext.close();
    }
}
