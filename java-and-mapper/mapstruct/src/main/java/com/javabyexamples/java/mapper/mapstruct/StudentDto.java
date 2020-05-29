package com.javabyexamples.java.mapper.mapstruct;

public class StudentDto {

    private String firstName;
    private String surname;
    private SchoolDto school;

    public StudentDto() {
    }

    public StudentDto(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public SchoolDto getSchool() {
        return school;
    }

    public void setSchool(SchoolDto school) {
        this.school = school;
    }
}
