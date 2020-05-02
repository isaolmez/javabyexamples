package com.javabyexamples.java.test.mockito.model;

public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public Person update(Person person) {
        return personRepository.update(person);
    }

    public Person select(Person first, Person second, Person third) {
        return personRepository.select(first, second, third);
    }

    public void delete(Person person) {
        person.setName("deleted");
        personRepository.delete(person);
    }
}
