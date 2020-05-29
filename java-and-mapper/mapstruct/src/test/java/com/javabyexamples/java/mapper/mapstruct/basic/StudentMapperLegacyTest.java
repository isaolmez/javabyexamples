package com.javabyexamples.java.mapper.mapstruct.basic;

import static org.assertj.core.api.Assertions.assertThat;

import com.javabyexamples.java.mapper.mapstruct.School;
import com.javabyexamples.java.mapper.mapstruct.Student;
import com.javabyexamples.java.mapper.mapstruct.StudentDto;
import org.junit.jupiter.api.Test;

class StudentMapperLegacyTest {

    private final StudentMapperLegacy mapper = StudentMapperLegacy.INSTANCE;

    @Test
    public void testToStudentDto() {
        final Student student = new Student("Jack", "Jones");
        final School school = new School("Little Bees");
        student.setSchool(school);

        final StudentDto studentDto = mapper.toStudentDto(student);

        assertThat(studentDto.getFirstName()).isEqualTo(student.getFirstName());
        assertThat(studentDto.getSurname()).isEqualTo(student.getLastName());
        assertThat(studentDto.getSchool().getName()).isEqualTo(student.getSchool().getName());
    }
}
