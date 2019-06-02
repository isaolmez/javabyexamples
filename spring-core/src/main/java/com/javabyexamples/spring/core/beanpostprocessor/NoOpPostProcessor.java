package com.javabyexamples.spring.core.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NoOpPostProcessor implements BeanPostProcessor, Ordered {

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof GreetingService) {
            System.out.println("No op");
        }

        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof GreetingService) {
            System.out.println("No op");
        }

        return bean;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
