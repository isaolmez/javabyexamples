package com.javabyexamples.spring.core.jsr330;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class JsrFieldInjectionPersonService {

    @Inject
    private PersonRepository personRepository;
}
