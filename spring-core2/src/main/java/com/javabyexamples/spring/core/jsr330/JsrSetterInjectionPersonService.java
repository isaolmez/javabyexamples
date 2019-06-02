package com.javabyexamples.spring.core.jsr330;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class JsrSetterInjectionPersonService {

    private PersonRepository personRepository;

    @Inject
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
