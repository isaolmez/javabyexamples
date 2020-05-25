package com.javabyexamples.java.mapper.orika.basic;

public class PersonHelper {

    public static Person getPerson() {
        final Person person = new Person();
        person.setFirstName("john");
        person.setLastName("doe");
        person.setAge(25);
        return person;
    }
}
