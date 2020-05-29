package com.javabyexamples.java.mapper.mapstruct.custom.mappingmethod;

import com.javabyexamples.java.mapper.mapstruct.School;
import com.javabyexamples.java.mapper.mapstruct.SchoolDto;
import com.javabyexamples.java.mapper.mapstruct.Student;
import com.javabyexamples.java.mapper.mapstruct.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(source = "lastName", target = "surname")
    StudentDto toStudentDto(Student student);

    @Mapping(source = "studentCount", target = "count")
    SchoolDto toSchoolDto(School school);
}
