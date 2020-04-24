package com.javabyexamples.spring.core.usingbean.autowirecandidate;

import org.springframework.stereotype.Component;

@Component
public class DefaultPersonService implements PersonService {

    private final PersonRepository personRepository;

    public DefaultPersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void perform() {
        System.out.println("Performing...");
        personRepository.save();
    }
}
