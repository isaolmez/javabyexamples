package com.javabyexamples.java.mapper.mapstruct.custom.concretemethod;

import static org.assertj.core.api.Assertions.assertThat;

import com.javabyexamples.java.mapper.mapstruct.Student;
import com.javabyexamples.java.mapper.mapstruct.StudentDto;
import com.javabyexamples.java.mapper.mapstruct.TestHelper;
import org.junit.jupiter.api.Test;

class StudentMapperWithConcreteMethodTest {

    private StudentMapperWithConcreteMethod mapper = StudentMapperWithConcreteMethod.INSTANCE;

    @Test
    public void testToStudentDto() {
        final Student student = TestHelper.getStudent();

        final StudentDto studentDto = mapper.toStudentDto(student);

        assertThat(studentDto.getFirstName()).isEqualTo(student.getFirstName());
        assertThat(studentDto.getSurname()).isEqualTo(student.getLastName());
        assertThat(studentDto.getSchool().getName()).isEqualTo(student.getSchool().getName());
        assertThat(studentDto.getSchool().getCity()).isEqualTo(student.getSchool().getCity());
        assertThat(studentDto.getSchool().getCount()).isEqualTo(student.getSchool().getStudentCount());
    }
}
