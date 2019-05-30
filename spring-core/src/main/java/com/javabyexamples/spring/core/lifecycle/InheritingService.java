package com.javabyexamples.spring.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InheritingService implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing " + getClass().getSimpleName());
    }

    @Override
    public void destroy() {
        System.out.println("Destroying " + getClass().getSimpleName());
    }
}
