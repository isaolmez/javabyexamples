package com.javabyexamples.spring.core.conditional.configurationcondition;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(Application.class);

        final AuditService auditService = applicationContext.getBean(AuditService.class);
        auditService.audit();

        applicationContext.close();
    }
}
