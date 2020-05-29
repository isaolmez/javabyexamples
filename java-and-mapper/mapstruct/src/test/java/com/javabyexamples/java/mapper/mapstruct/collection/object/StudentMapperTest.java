package com.javabyexamples.java.mapper.mapstruct.collection.object;

import static org.assertj.core.api.Assertions.assertThat;

import com.javabyexamples.java.mapper.mapstruct.collection.Exam;
import com.javabyexamples.java.mapper.mapstruct.collection.Student;
import com.javabyexamples.java.mapper.mapstruct.collection.StudentDto;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class StudentMapperTest {

    private final StudentMapper mapper = StudentMapper.INSTANCE;

    @Test
    public void testToStudentDto() {
        final Student student = getStudent();

        final StudentDto studentDto = mapper.toStudentDto(student);

        assertThat(studentDto.getExams().get(0).getGrades()).containsExactly("15", "25", "35");
        assertThat(studentDto.getExams().get(1).getGrades()).containsExactly("10", "20", "30");
    }

    private Student getStudent() {
        final Exam exam1 = new Exam(Arrays.asList(15, 25, 35));
        final Exam exam2 = new Exam(Arrays.asList(10, 20, 30));

        final Student student = new Student();
        student.setExams(Arrays.asList(exam1, exam2));
        return student;
    }
}
