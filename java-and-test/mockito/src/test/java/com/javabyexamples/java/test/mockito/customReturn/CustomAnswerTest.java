package com.javabyexamples.java.test.mockito.customReturn;

import com.javabyexamples.java.test.mockito.model.Person;
import com.javabyexamples.java.test.mockito.model.PersonRepository;
import com.javabyexamples.java.test.mockito.model.PersonService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
public class CustomAnswerTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    public void shouldReturnFirstPerson() {
        Person firstPerson = new Person("first");
        Person secondPerson = new Person("second");
        Person thirdPerson = new Person("third");
        Mockito.when(personRepository.select(firstPerson, secondPerson, thirdPerson))
          .thenAnswer(new Answer<Person>() {
              @Override
              public Person answer(InvocationOnMock invocation) throws Throwable {
                  return invocation.getArgumentAt(0, Person.class);
              }
          });

        Person actual = personService.select(firstPerson, secondPerson, thirdPerson);

        Assertions.assertThat(actual).isEqualTo(firstPerson);
    }

    @Test
    public void shouldReturnNewPerson() {
        Person firstPerson = new Person("first");
        Person secondPerson = new Person("second");
        Person thirdPerson = new Person("third");
        Person other = new Person("other");
        Mockito.when(personRepository.select(firstPerson, secondPerson, thirdPerson))
          .thenAnswer(new Answer<Person>() {
              @Override
              public Person answer(InvocationOnMock invocation) throws Throwable {
                  return other;
              }
          });

        Person actual = personService.select(firstPerson, secondPerson, thirdPerson);

        Assertions.assertThat(actual).isEqualTo(other);
    }

    @Test
    public void shouldCallRealMethod() {
        Person firstPerson = new Person("first");
        Person secondPerson = new Person("second");
        Person thirdPerson = new Person("third");
        Person other = new Person("other");
        Mockito.when(personRepository.select(firstPerson, secondPerson, thirdPerson))
          .thenAnswer(new Answer<Person>() {
              @Override
              public Person answer(InvocationOnMock invocation) throws Throwable {
                  return (Person) invocation.callRealMethod();
              }
          });

        Person actual = personService.select(firstPerson, secondPerson, thirdPerson);

        Assertions.assertThat(actual).isEqualTo(firstPerson);
    }
}
