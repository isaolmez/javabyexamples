package com.javabyexamples.java.mapper.orika.mapping;

public class PersonHelper {

    public static Person getPerson() {
        final Person person = new Person();
        person.setFirstName("john");
        person.setLastName("doe");
        person.setAge(25);
        final Address address = new Address();
        address.setCity("London");
        address.setPostalCode("0012");
        person.setAddress(address);
        final Address workAddress = new Address();
        workAddress.setCity("London");
        workAddress.setPostalCode("0013");
        person.setWorkAddress(workAddress);
        return person;
    }

    public static PersonDto getPersonDto() {
        final PersonDto personDto = new PersonDto();
        personDto.setName("john");
        personDto.setSurname("doe");
        personDto.setAge(25);
        personDto.setCity("London");
        return personDto;
    }
}
