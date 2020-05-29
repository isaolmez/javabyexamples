package com.javabyexamples.java.mapper.mapstruct;

public class StudentExtendedDto extends StudentDto {

    private String studentNumber;

    public StudentExtendedDto(String firstName, String surname, String studentNumber) {
        super(firstName, surname);
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}
