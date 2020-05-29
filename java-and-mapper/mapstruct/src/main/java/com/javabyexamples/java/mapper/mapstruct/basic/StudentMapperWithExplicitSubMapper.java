package com.javabyexamples.java.mapper.mapstruct.basic;

import com.javabyexamples.java.mapper.mapstruct.School;
import com.javabyexamples.java.mapper.mapstruct.SchoolDto;
import com.javabyexamples.java.mapper.mapstruct.Student;
import com.javabyexamples.java.mapper.mapstruct.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper example mostly same with the case 1 Java 8+ does not need Mappings container annotation
 */
@Mapper(disableSubMappingMethodsGeneration = true)
public interface StudentMapperWithExplicitSubMapper {

    StudentMapperWithExplicitSubMapper INSTANCE = Mappers.getMapper(StudentMapperWithExplicitSubMapper.class);

    @Mapping(source = "lastName", target = "surname")
    StudentDto toStudentDto(Student student);

    @Mapping(source = "studentCount", target = "count")
    SchoolDto schoolToSchoolDto(School school);
}
