package com.javabyexamples.spring.core.awareinterfaces;

import javax.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class LoggerService implements ApplicationContextAware, EnvironmentAware, BeanNameAware,
  ApplicationEventPublisherAware {

    private ApplicationContext applicationContext;
    private Environment environment;
    private String beanName;
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void init() {
        System.out.println("ApplicationContext: " + applicationContext);
        System.out.println("Environment: " + environment);
        System.out.println("Bean name: " + beanName);
        System.out.println("ApplicationEventPublisher: " + applicationEventPublisher);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
