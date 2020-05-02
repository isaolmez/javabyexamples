package com.javabyexamples.java.test.mockito.expectationOverride;

import com.javabyexamples.java.test.mockito.model.Person;
import com.javabyexamples.java.test.mockito.model.PersonRepository;
import com.javabyexamples.java.test.mockito.model.PersonService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExpectationOverrideTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    private Person defaultPerson = new Person("default");

    @Before
    public void setUp() {
        Mockito.when(personRepository.update(Mockito.any(Person.class))).thenReturn(defaultPerson);
    }

    @Test
    public void shouldReturnDefaultPerson() {
        Person actual = personService.update(new Person("test"));

        Assertions.assertThat(actual).isEqualTo(defaultPerson);
    }

    @Test
    public void shouldOverrideDefaultPerson() {
        Person expectedPerson = new Person("override");
        Mockito.when(personRepository.update(Mockito.any(Person.class))).thenReturn(expectedPerson);

        Person actualPerson = personService.update(new Person("test"));

        Assertions.assertThat(actualPerson).isEqualTo(expectedPerson);
    }
}
