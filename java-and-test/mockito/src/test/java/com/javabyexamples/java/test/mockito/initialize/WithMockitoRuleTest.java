package com.javabyexamples.java.test.mockito.initialize;

import com.javabyexamples.java.test.mockito.model.Person;
import com.javabyexamples.java.test.mockito.model.PersonRepository;
import com.javabyexamples.java.test.mockito.model.PersonService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class WithMockitoRuleTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    public void shouldSavePerson() {
        Person person = new Person("test");

        personService.save(person);

        Mockito.verify(personRepository).save(person);
    }
}
