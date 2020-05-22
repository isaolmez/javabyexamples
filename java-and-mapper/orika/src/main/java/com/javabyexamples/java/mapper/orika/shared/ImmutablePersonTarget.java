package com.javabyexamples.java.mapper.orika.shared;

public final class ImmutablePersonTarget {

    private final String name;
    private final String surname;
    private final int age;

    public ImmutablePersonTarget(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getAge() {
        return this.age;
    }
}
