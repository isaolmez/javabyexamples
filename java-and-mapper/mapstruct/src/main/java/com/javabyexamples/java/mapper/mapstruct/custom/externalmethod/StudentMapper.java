package com.javabyexamples.java.mapper.mapstruct.custom.externalmethod;

import com.javabyexamples.java.mapper.mapstruct.Student;
import com.javabyexamples.java.mapper.mapstruct.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Reference another mapper by uses annotation attribute. This referenced mapper can be a Mapstruct mapper or another
 * mapper.
 */
@Mapper(uses = ExternalMapperHelper.class)
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(source = "lastName", target = "surname")
    StudentDto toStudentDto(Student student);
}
