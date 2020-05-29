package com.javabyexamples.java.mapper.mapstruct.custom.defaultmethod;

import com.javabyexamples.java.mapper.mapstruct.School;
import com.javabyexamples.java.mapper.mapstruct.SchoolDto;
import com.javabyexamples.java.mapper.mapstruct.Student;
import com.javabyexamples.java.mapper.mapstruct.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapperWithDefaultMethod {

    StudentMapperWithDefaultMethod INSTANCE = Mappers.getMapper(StudentMapperWithDefaultMethod.class);

    @Mapping(source = "lastName", target = "surname")
    StudentDto toStudentDto(Student student);

    default SchoolDto toSchoolDto(School school) {
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
