package com.javabyexamples.java.mapper.mapstruct.custom.ignore;

import static org.assertj.core.api.Assertions.assertThat;

import com.javabyexamples.java.mapper.mapstruct.Student;
import com.javabyexamples.java.mapper.mapstruct.StudentDto;
import com.javabyexamples.java.mapper.mapstruct.TestHelper;
import org.junit.jupiter.api.Test;

class StudentMapperWithIgnoreTest {

    private final StudentMapperWithIgnore mapper = StudentMapperWithIgnore.INSTANCE;

    @Test
    public void testToStudentDto() {
        final Student student = TestHelper.getStudent();

        final StudentDto studentDto = mapper.toStudentDto(student);

        assertThat(studentDto.getFirstName()).isEqualTo(student.getFirstName());
        assertThat(studentDto.getSurname()).isEqualTo(student.getLastName());
        assertThat(studentDto.getSchool()).isNull();
    }
}
