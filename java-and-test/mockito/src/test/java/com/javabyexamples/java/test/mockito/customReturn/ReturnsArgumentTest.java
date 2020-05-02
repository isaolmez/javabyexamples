package com.javabyexamples.java.test.mockito.customReturn;

import com.javabyexamples.java.test.mockito.model.Person;
import com.javabyexamples.java.test.mockito.model.PersonRepository;
import com.javabyexamples.java.test.mockito.model.PersonService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReturnsArgumentTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    public void shouldReturnFirstArg() {
        Person firstPerson = new Person("first");
        Person secondPerson = new Person("second");
        Person thirdPerson = new Person("third");
        Mockito.when(personRepository.select(firstPerson, secondPerson, thirdPerson))
          .then(AdditionalAnswers.returnsFirstArg());

        Person actual = personService.select(firstPerson, secondPerson, thirdPerson);

        Assertions.assertThat(actual).isEqualTo(firstPerson);
    }

    @Test
    public void shouldReturnSecondArg() {
        Person firstPerson = new Person("first");
        Person secondPerson = new Person("second");
        Person thirdPerson = new Person("third");
        Mockito.when(personRepository.select(firstPerson, secondPerson, thirdPerson))
          .then(AdditionalAnswers.returnsSecondArg());

        Person actual = personService.select(firstPerson, secondPerson, thirdPerson);

        Assertions.assertThat(actual).isEqualTo(secondPerson);
    }

    @Test
    public void shouldReturnLastArg() {
        Person firstPerson = new Person("first");
        Person secondPerson = new Person("second");
        Person thirdPerson = new Person("third");
        Mockito.when(personRepository.select(firstPerson, secondPerson, thirdPerson))
          .then(AdditionalAnswers.returnsLastArg());

        Person actual = personService.select(firstPerson, secondPerson, thirdPerson);

        Assertions.assertThat(actual).isEqualTo(thirdPerson);
    }

    @Test
    public void shouldReturnArgAt() {
        Person firstPerson = new Person("first");
        Person secondPerson = new Person("second");
        Person thirdPerson = new Person("third");
        Mockito.when(personRepository.select(firstPerson, secondPerson, thirdPerson))
          .then(AdditionalAnswers.returnsArgAt(1));

        Person actual = personService.select(firstPerson, secondPerson, thirdPerson);

        Assertions.assertThat(actual).isEqualTo(secondPerson);
    }
}
