package com.javabyexamples.java.mapper.mapstruct.custom.externalmethod;

import com.javabyexamples.java.mapper.mapstruct.School;
import com.javabyexamples.java.mapper.mapstruct.SchoolDto;

public class ExternalMapperHelper {

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
