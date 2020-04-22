package com.javabyexamples.spring.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class MixedService implements InitializingBean, DisposableBean {

    @PostConstruct
    public void annotationInit() {
        System.out.println("Initializing since @PostConstruct");
    }

    @PreDestroy
    public void annotationDestroy() {
        System.out.println("Destroying since @PreDestroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Destroying since DisposableBean");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing since InitializingBean");
    }

    public void customInit() {
        System.out.println("Initializing since init-method");
    }

    public void customDestroy() {
        System.out.println("Destroying since destroy-method");
    }
}
