package com.javabyexamples.java.mapper.mapstruct.basic;

import com.javabyexamples.java.mapper.mapstruct.Student;
import com.javabyexamples.java.mapper.mapstruct.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Mapper with Mappings container annotation
 */
@Mapper
public interface StudentMapperLegacy {

    StudentMapperLegacy INSTANCE = Mappers.getMapper(StudentMapperLegacy.class);

    @Mappings({
      @Mapping(source = "lastName", target = "surname")
    })
    StudentDto toStudentDto(Student student);

}
