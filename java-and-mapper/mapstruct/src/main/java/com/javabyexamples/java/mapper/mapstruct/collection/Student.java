package com.javabyexamples.java.mapper.mapstruct.collection;

import java.util.List;

public class Student {

    private List<Exam> exams;

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
