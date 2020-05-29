package com.javabyexamples.java.mapper.mapstruct.existing;

import com.javabyexamples.java.mapper.mapstruct.Student;
import com.javabyexamples.java.mapper.mapstruct.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(source = "lastName", target = "surname")
    void toExistingStudentDto(Student student, @MappingTarget StudentDto studentDto);
}
