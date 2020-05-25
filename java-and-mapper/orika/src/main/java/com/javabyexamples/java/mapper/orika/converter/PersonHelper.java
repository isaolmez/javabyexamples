package com.javabyexamples.java.mapper.orika.converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class PersonHelper {

    public static Person getPerson() {
        final Person person = new Person();
        person.setFirstName("john");
        person.setLastName("doe");
        person.setAge(25);
        person.setBirthDate(new Date(1000));
        return person;
    }

    public static PersonDto getPersonDto() {
        final PersonDto personDto = new PersonDto();
        personDto.setName("john");
        personDto.setSurname("doe");
        personDto.setAge(25);
        personDto.setBirthDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(1000), ZoneOffset.UTC));
        return personDto;
    }
}
