package com.javabyexamples.spring.core.beanpostprocessor.logging;

import com.javabyexamples.spring.core.beanpostprocessor.GreetingService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LoggingBeanPostProcessor implements BeanPostProcessor {

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof GreetingService) {
            return proxiedBean((GreetingService) bean);
        }

        return bean;
    }

    private Object proxiedBean(GreetingService bean) {
        ProxyFactory proxyFactory = new ProxyFactory(bean);
        proxyFactory.addAdvice(new LoggingInterceptor());
        return proxyFactory.getProxy();
    }

    private static class LoggingInterceptor implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
            System.out.println("Before greeting");
            Object returnValue = methodInvocation.proceed();
            System.out.println("After greeting");
            return returnValue;
        }
    }
}
