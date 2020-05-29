package com.javabyexamples.java.mapper.mapstruct.custom.concretemethod;

import com.javabyexamples.java.mapper.mapstruct.School;
import com.javabyexamples.java.mapper.mapstruct.SchoolDto;
import com.javabyexamples.java.mapper.mapstruct.Student;
import com.javabyexamples.java.mapper.mapstruct.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class StudentMapperWithConcreteMethod {

    public static final StudentMapperWithConcreteMethod INSTANCE = Mappers.getMapper(StudentMapperWithConcreteMethod.class);

    @Mapping(source = "lastName", target = "surname")
    public abstract StudentDto toStudentDto(Student student);

    public SchoolDto toSchoolDto(School school) {
        if (school == null) {
            return null;
        }

        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setName(school.getName());
        schoolDto.setCity(school.getCity());
        schoolDto.setCount(school.getStudentCount());
        return schoolDto;
    }
}
