package com.javabyexamples.java.mapper.mapstruct.existing;

import static org.assertj.core.api.Assertions.assertThat;

import com.javabyexamples.java.mapper.mapstruct.Student;
import com.javabyexamples.java.mapper.mapstruct.StudentDto;
import com.javabyexamples.java.mapper.mapstruct.TestHelper;
import org.junit.jupiter.api.Test;

class StudentMapperTest {

    private final StudentMapper studentMapper = StudentMapper.INSTANCE;

    @Test
    public void testToExistingStudentDto() {
        final Student student = TestHelper.getStudent();

        final StudentDto existing = new StudentDto();
        existing.setFirstName("Existing");

        studentMapper.toExistingStudentDto(student, existing);

        assertThat(existing.getFirstName()).isEqualTo(student.getFirstName());
        assertThat(existing.getSurname()).isEqualTo(student.getLastName());
        assertThat(existing.getSchool().getName()).isEqualTo(student.getSchool().getName());
    }
}
