package com.javabyexamples.spring.core.usingbean.autowirecandidate;

public class MockPersonRepository implements PersonRepository {

    @Override
    public void save() {
        System.out.println("Mocking...");
    }
}
