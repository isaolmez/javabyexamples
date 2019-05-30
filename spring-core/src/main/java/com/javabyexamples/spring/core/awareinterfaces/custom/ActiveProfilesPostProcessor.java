package com.javabyexamples.spring.core.awareinterfaces.custom;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ActiveProfilesPostProcessor implements BeanPostProcessor {

    private final Environment environment;

    @Autowired
    public ActiveProfilesPostProcessor(Environment environment) {
        this.environment = environment;
    }

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ActiveProfilesAware) {
            List<String> activeProfiles = Arrays.stream(environment.getActiveProfiles()).collect(Collectors.toList());
            ((ActiveProfilesAware) bean).setActiveProfiles(activeProfiles);
            return bean;
        }

        return bean;
    }
}
