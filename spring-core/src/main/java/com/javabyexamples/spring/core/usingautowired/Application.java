package com.javabyexamples.spring.core.usingautowired;

import com.javabyexamples.spring.core.usingautowired.wiremultiple.GoodManager;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);

        ConstructorInjectedStoreService constructorInjectedStoreService = applicationContext
          .getBean(ConstructorInjectedStoreService.class);
        constructorInjectedStoreService.start();

        FieldInjectedStoreService fieldInjectedStoreService = applicationContext
          .getBean(FieldInjectedStoreService.class);
        fieldInjectedStoreService.start();

        SetterInjectedStoreService setterInjectedStoreService = applicationContext
          .getBean(SetterInjectedStoreService.class);
        setterInjectedStoreService.start();

        WithoutAutowiredStoreService withoutAutowiredStoreService = applicationContext
          .getBean(WithoutAutowiredStoreService.class);
        withoutAutowiredStoreService.start();

        OptionalDependencyStoreService optionalDependencyStoreService = applicationContext
          .getBean(OptionalDependencyStoreService.class);
        optionalDependencyStoreService.start();

        MixedInjectedStoreService mixedInjectedStoreService = applicationContext
          .getBean(MixedInjectedStoreService.class);
        mixedInjectedStoreService.start();

        GoodManager goodManager = applicationContext.getBean(GoodManager.class);
        goodManager.list();

        applicationContext.close();
    }
}
