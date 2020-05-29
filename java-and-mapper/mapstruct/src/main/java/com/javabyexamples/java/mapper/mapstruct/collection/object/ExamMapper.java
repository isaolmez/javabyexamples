package com.javabyexamples.java.mapper.mapstruct.collection.object;

import com.javabyexamples.java.mapper.mapstruct.collection.Exam;
import com.javabyexamples.java.mapper.mapstruct.collection.ExamDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExamMapper {

    ExamMapper INSTANCE = Mappers.getMapper(ExamMapper.class);

    ExamDto toExamDto(Exam exam);

    List<ExamDto> toExamDtos(List<Exam> exams);
}
