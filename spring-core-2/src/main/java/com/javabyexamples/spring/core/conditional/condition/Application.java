package com.javabyexamples.spring.core.conditional.condition;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(Application.class);

        applicationContext.close();
    }

    @Bean
    @Conditional(AuditEnabledCondition.class)
    public DefaultAuditService anotherAuditService() {
        return new DefaultAuditService();
    }
}
