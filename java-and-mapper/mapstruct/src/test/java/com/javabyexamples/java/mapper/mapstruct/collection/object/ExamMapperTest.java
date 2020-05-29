package com.javabyexamples.java.mapper.mapstruct.collection.object;

import static org.assertj.core.api.Assertions.assertThat;

import com.javabyexamples.java.mapper.mapstruct.collection.Exam;
import com.javabyexamples.java.mapper.mapstruct.collection.ExamDto;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class ExamMapperTest {

    private final ExamMapper mapper = ExamMapper.INSTANCE;

    @Test
    public void testToExamDto() {
        final Exam exam = new Exam(Arrays.asList(15, 25, 35));

        final ExamDto examDto = mapper.toExamDto(exam);

        assertThat(examDto.getGrades()).containsExactly("15", "25", "35");
    }

    @Test
    public void testToExamDtos() {
        final Exam exam1 = new Exam(Arrays.asList(15, 25, 35));
        final Exam exam2 = new Exam(Arrays.asList(10, 20, 30));
        final List<Exam> exams = Arrays.asList(exam1, exam2);

        final List<ExamDto> examDtos = mapper.toExamDtos(exams);

        assertThat(examDtos.get(0).getGrades()).containsExactly("15", "25", "35");
        assertThat(examDtos.get(1).getGrades()).containsExactly("10", "20", "30");
    }
}
