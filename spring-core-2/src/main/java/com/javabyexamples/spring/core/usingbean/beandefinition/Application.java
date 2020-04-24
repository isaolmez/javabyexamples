package com.javabyexamples.spring.core.usingbean.beandefinition;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class Application {

    public static void main(String[] args) {
        GenericApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(ServiceConfiguration.class);

        final ServiceClient serviceClient = applicationContext.getBean(ServiceClient.class);
        final BeanDefinition beanDefinition = applicationContext.getBeanDefinition("serviceOne");

        applicationContext.close();
    }
}
