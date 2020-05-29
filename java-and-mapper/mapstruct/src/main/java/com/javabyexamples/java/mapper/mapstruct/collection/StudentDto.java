package com.javabyexamples.java.mapper.mapstruct.collection;

import java.util.List;

public class StudentDto {

    private List<ExamDto> exams;

    public List<ExamDto> getExams() {
        return exams;
    }

    public void setExams(List<ExamDto> exams) {
        this.exams = exams;
    }
}
