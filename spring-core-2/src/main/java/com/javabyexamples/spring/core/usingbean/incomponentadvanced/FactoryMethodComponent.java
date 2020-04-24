package com.javabyexamples.spring.core.usingbean.incomponentadvanced;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
public class FactoryMethodComponent {

    private static int i;

    @Bean
    @Qualifier("public")
    public PersonBean publicInstance() {
        return new PersonBean("publicInstance");
    }

    // use of a custom qualifier and autowiring of method parameters
    @Bean
    protected PersonBean protectedInstance(
      @Qualifier("public") PersonBean spouse,
      @Value("#{privateInstance.age}") String country) {
        PersonBean personBean = new PersonBean("protectedInstance", 1);
        personBean.setSpouse(spouse);
        personBean.setCountry(country);
        return personBean;
    }

    @Bean
    private PersonBean privateInstance() {
        return new PersonBean("privateInstance", i++);
    }

    @Bean
    @RequestScope
    public PersonBean requestScopedInstance() {
        return new PersonBean("requestScopedInstance", 3);
    }
}
