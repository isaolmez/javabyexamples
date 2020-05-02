package com.javabyexamples.java.test.mockito.voidMethod;

import com.javabyexamples.java.test.mockito.model.Person;
import com.javabyexamples.java.test.mockito.model.PersonRepository;
import com.javabyexamples.java.test.mockito.model.PersonService;
import java.util.concurrent.atomic.AtomicInteger;
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
public class VoidMethodTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test(expected = RuntimeException.class)
    public void shouldThrowException() {
        Mockito.doThrow(RuntimeException.class).when(personRepository).save(Mockito.any(Person.class));

        personRepository.save(new Person("test"));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowGivenException() {
        Mockito.doThrow(new RuntimeException("Planned exception")).when(personRepository)
          .save(Mockito.any(Person.class));

        personRepository.save(new Person("test"));
    }

    @Test
    public void shouldPrint() {
        final AtomicInteger counter = new AtomicInteger();
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                counter.incrementAndGet();
                return null;
            }
        }).when(personRepository).save(Mockito.any(Person.class));

        personRepository.save(new Person("test"));

        Assertions.assertThat(counter.get()).isEqualTo(1);
    }

    @Test(expected = RuntimeException.class)
    public void shouldDoNothing() {
        Mockito.doNothing()
          .doThrow(RuntimeException.class)
          .when(personRepository).save(Mockito.any(Person.class));
        Person firstPerson = new Person("first");
        Person secondPerson = new Person("second");

        personRepository.save(firstPerson);

        Mockito.verify(personRepository).save(firstPerson);

        personRepository.save(new Person("second"));
    }
}
