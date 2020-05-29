package com.javabyexamples.java.mapper.mapstruct.collection;

import java.util.List;

public class Exam {

    private List<Integer> grades;

    public Exam() {
    }

    public Exam(List<Integer> grades) {
        this.grades = grades;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Exam [grades=" + grades + "]";
    }
}
