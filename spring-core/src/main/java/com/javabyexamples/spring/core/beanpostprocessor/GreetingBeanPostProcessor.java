package com.javabyexamples.spring.core.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class GreetingBeanPostProcessor implements BeanPostProcessor, Ordered {

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof GreetingService) {
            System.out.printf("postProcessBeforeInitialization() in %s for %s%n", getClass().getSimpleName(), beanName);
        }

        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof GreetingService) {
            System.out.printf("postProcessAfterInitialization() in %s for %s%n", getClass().getSimpleName(), beanName);
        }

        return bean;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
