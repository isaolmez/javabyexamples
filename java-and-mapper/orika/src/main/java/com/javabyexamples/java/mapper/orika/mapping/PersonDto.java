package com.javabyexamples.java.mapper.orika.mapping;

public class PersonDto {

    private String name;
    private String surname;
    private int age;
    private String city;
    private AddressDto workAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public AddressDto getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(AddressDto workAddress) {
        this.workAddress = workAddress;
    }
}
