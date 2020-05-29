package com.javabyexamples.java.mapper.mapstruct.collection;

import java.util.List;

public class ExamDto {

    private List<String> grades;

    public ExamDto() {
    }

    public ExamDto(List<String> grades) {
        this.grades = grades;
    }

    public List<String> getGrades() {
        return grades;
    }

    public void setGrades(List<String> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "ExamDto{" +
          "grades=" + grades +
          '}';
    }
}
