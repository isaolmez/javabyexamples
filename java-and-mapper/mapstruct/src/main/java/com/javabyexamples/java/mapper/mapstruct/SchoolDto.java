package com.javabyexamples.java.mapper.mapstruct;

public class SchoolDto {

    private String name;
    private String city;
    private int count;

    public SchoolDto() {
    }

    public SchoolDto(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "SchoolDto{" +
          "name='" + name + '\'' +
          '}';
    }
}
