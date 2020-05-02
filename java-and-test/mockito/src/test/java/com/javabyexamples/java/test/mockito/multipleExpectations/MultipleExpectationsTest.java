package com.javabyexamples.java.test.mockito.multipleExpectations;

import com.javabyexamples.java.test.mockito.model.Person;
import com.javabyexamples.java.test.mockito.model.PersonRepository;
import com.javabyexamples.java.test.mockito.model.PersonService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MultipleExpectationsTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    public void shouldDefineMultipleExpectations() {
        Person firstExpected = new Person("first");
        Person secondExpected = new Person("second");
        Mockito.when(personRepository.update(Mockito.any(Person.class))).thenReturn(firstExpected)
          .thenReturn(secondExpected);

        Person firstPerson = personService.update(new Person("test"));

        Assertions.assertThat(firstPerson).isEqualTo(firstExpected);

        Person secondPerson = personService.update(new Person("test"));

        Assertions.assertThat(secondPerson).isEqualTo(secondExpected);
    }

    @Test
    public void shouldNotDefineMultipleExpectations() {
        Person firstExpected = new Person("first");
        Person secondExpected = new Person("second");
        Mockito.when(personRepository.update(Mockito.any(Person.class))).thenReturn(firstExpected);
        Mockito.when(personRepository.update(Mockito.any(Person.class))).thenReturn(secondExpected);

        Person firstPerson = personService.update(new Person("test"));

        Assertions.assertThat(firstPerson).isEqualTo(secondExpected);

        Person secondPerson = personService.update(new Person("test"));

        Assertions.assertThat(secondPerson).isEqualTo(secondExpected);
    }
}
