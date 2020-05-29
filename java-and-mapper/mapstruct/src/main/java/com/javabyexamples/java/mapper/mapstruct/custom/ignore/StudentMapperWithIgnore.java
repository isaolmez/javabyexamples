package com.javabyexamples.java.mapper.mapstruct.custom.ignore;

import com.javabyexamples.java.mapper.mapstruct.Student;
import com.javabyexamples.java.mapper.mapstruct.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapperWithIgnore {

    StudentMapperWithIgnore INSTANCE = Mappers.getMapper(StudentMapperWithIgnore.class);

    @Mapping(source = "lastName", target = "surname")
    @Mapping(source = "school", target = "school", ignore = true)
    StudentDto toStudentDto(Student student);
}
