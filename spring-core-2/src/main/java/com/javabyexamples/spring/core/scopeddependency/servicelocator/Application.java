package com.javabyexamples.spring.core.scopeddependency.servicelocator;

import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(Application.class);

        final ClientService clientService = applicationContext.getBean(ClientService.class);
        clientService.doWork();
        clientService.doWork();
        clientService.doWork();

        applicationContext.close();
    }

    @Bean
    public ServiceLocatorFactoryBean timerLocator() {
        final ServiceLocatorFactoryBean locator = new ServiceLocatorFactoryBean();
        locator.setServiceLocatorInterface(TimerFactory.class);
        return locator;
    }
}
