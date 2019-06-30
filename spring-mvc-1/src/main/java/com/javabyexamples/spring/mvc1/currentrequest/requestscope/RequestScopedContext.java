package com.javabyexamples.spring.mvc1.currentrequest.requestscope;

import com.javabyexamples.spring.mvc1.currentrequest.Person;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class RequestScopedContext {

    private Person person;

    public void set(Person person) {
        this.person = person;
    }

    public Person get() {
        return person;
    }
}
