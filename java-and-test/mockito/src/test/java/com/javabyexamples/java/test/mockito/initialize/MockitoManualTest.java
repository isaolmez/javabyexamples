package com.javabyexamples.java.test.mockito.initialize;

import com.javabyexamples.java.test.mockito.model.Person;
import com.javabyexamples.java.test.mockito.model.PersonRepository;
import com.javabyexamples.java.test.mockito.model.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MockitoManualTest {

    private PersonService personService;

    private PersonRepository personRepository;

    @Before
    public void setUp() {
        personRepository = Mockito.mock(PersonRepository.class);
        personService = new PersonService(personRepository);
    }

    @Test
    public void shouldSavePerson() {
        Person person = new Person("test");

        personService.save(person);

        Mockito.verify(personRepository).save(person);
    }
}
