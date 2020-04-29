package com.javabyexamples.spring.core.scopeddependency.beanfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientService {

    private final BeanFactory beanFactory;

    public ClientService(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void doWork() {
        System.out.println(getCounter().count());
    }

    private Counter getCounter() {
        return beanFactory.getBean(Counter.class);
    }
}
