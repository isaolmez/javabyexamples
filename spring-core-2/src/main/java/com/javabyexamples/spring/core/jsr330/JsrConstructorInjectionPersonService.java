package com.javabyexamples.spring.core.jsr330;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class JsrConstructorInjectionPersonService {

    private final PersonRepository personRepository;

    @Inject
    public JsrConstructorInjectionPersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
