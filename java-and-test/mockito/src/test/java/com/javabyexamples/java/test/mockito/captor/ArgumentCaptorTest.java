package com.javabyexamples.java.test.mockito.captor;

import com.javabyexamples.java.test.mockito.model.Person;
import com.javabyexamples.java.test.mockito.model.PersonRepository;
import com.javabyexamples.java.test.mockito.model.PersonService;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ArgumentCaptorTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Captor
    private ArgumentCaptor<Person> captor;

    @Test
    public void shouldCapture() {
        Person person = new Person("test");

        personService.delete(person);

        Mockito.verify(personRepository).delete(captor.capture());

        Person captured = captor.getValue();

        Assertions.assertThat(captured.getName()).isEqualTo("deleted");
    }

    @Test
    public void shouldCaptureMultipleTimes() {
        personService.delete(new Person("test"));
        personService.delete(new Person("test"));

        Mockito.verify(personRepository, Mockito.times(2)).delete(captor.capture());

        List<Person> allValues = captor.getAllValues();

        for (Person captured : allValues) {
            Assertions.assertThat(captured.getName()).isEqualTo("deleted");
        }
    }

    @Test
    public void shouldCaptureManually() {
        ArgumentCaptor<Person> argumentCaptor = ArgumentCaptor.forClass(Person.class);
        Person person = new Person("test");

        personService.delete(person);

        Mockito.verify(personRepository).delete(argumentCaptor.capture());

        Person captured = argumentCaptor.getValue();

        Assertions.assertThat(captured.getName()).isEqualTo("deleted");
    }
}
