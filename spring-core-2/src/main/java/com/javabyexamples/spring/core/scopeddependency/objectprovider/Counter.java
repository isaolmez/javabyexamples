package com.javabyexamples.spring.core.scopeddependency.objectprovider;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Counter {

    private int current = 0;

    public int count() {
        return current++;
    }
}
