package com.javabyexamples.java.mapper.mapstruct.custom.decorator;

import com.javabyexamples.java.mapper.mapstruct.Student;
import com.javabyexamples.java.mapper.mapstruct.StudentDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Create a decorator class that adds additional custom mappings Need to specify "ignore=true" for driver
 */
@Mapper
@DecoratedWith(StudentMapperDecorator.class)
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(source = "lastName", target = "surname")
    StudentDto toStudentDto(Student student);
}
