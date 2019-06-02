package com.javabyexamples.spring.core.jsr330;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

@Named
public class JsrProviderInjectionPersonService {

    @Inject
    private Provider<PersonRepository> personRepositoryProvider;
}
