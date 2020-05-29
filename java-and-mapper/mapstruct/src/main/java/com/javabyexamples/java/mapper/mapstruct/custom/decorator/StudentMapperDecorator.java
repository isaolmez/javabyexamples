package com.javabyexamples.java.mapper.mapstruct.custom.decorator;

import com.javabyexamples.java.mapper.mapstruct.School;
import com.javabyexamples.java.mapper.mapstruct.SchoolDto;
import com.javabyexamples.java.mapper.mapstruct.Student;
import com.javabyexamples.java.mapper.mapstruct.StudentDto;

/**
 * Decorator class that defines additional custom mappings
 */
public abstract class StudentMapperDecorator implements StudentMapper {

    private StudentMapper delegate;

    public StudentMapperDecorator(StudentMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public StudentDto toStudentDto(Student student) {
        StudentDto studentDto = delegate.toStudentDto(student);
        if (student.getSchool() != null) {
            studentDto.setSchool(internalToSchoolDto(student.getSchool()));
        }

        return studentDto;
    }

    private SchoolDto internalToSchoolDto(School school) {
        if (school == null) {
            return null;
        }

        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setName(school.getName());
        schoolDto.setCity(school.getCity());
        schoolDto.setCount(school.getStudentCount());
        return schoolDto;
    }
}
