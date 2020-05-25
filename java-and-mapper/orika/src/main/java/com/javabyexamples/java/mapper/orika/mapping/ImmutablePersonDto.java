package com.javabyexamples.java.mapper.orika.mapping;

public final class ImmutablePersonDto {

    private final String name;
    private final String surname;
    private final int age;

    public ImmutablePersonDto(String name, String surname, int age) {
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
