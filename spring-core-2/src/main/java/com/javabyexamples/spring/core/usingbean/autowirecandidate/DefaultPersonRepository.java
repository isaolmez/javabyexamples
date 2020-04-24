package com.javabyexamples.spring.core.usingbean.autowirecandidate;

public class DefaultPersonRepository implements PersonRepository {

    @Override
    public void save() {
        System.out.println("Saving...");
    }
}
