package com.javabyexamples.java.mapper.mapstruct;

public class TestHelper {

    public static Student getStudent(){
        final Student student = new Student("Jack", "Jones");
        final School school = new School();
        school.setName("Little Bees");
        school.setCity("London");
        school.setStudentCount(100);
        student.setSchool(school);
        return student;
    }
}
