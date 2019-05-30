package com.javabyexamples.spring.core.javaconfig;

import java.util.ArrayList;
import java.util.List;

public class ClassService {

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("John"));
        students.add(new Student("Sarah"));
        return students;
    }
}
