package com.javabyexamples.java.mapper.orika.shared;

public class PersonHelper {

    public static PersonSource getPersonSource() {
        final PersonSource personSource = new PersonSource();
        personSource.setFirstName("john");
        personSource.setLastName("doe");
        personSource.setAge(25);
        return personSource;
    }

    public static PersonTarget getPersonTarget() {
        PersonTarget existingPersonTarget = new PersonTarget();
        existingPersonTarget.setAge(30);
        return existingPersonTarget;
    }
}
