package com.javabyexamples.java.mapper.mapstruct.collection.object;

import com.javabyexamples.java.mapper.mapstruct.collection.Student;
import com.javabyexamples.java.mapper.mapstruct.collection.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDto toStudentDto(Student student);
}
