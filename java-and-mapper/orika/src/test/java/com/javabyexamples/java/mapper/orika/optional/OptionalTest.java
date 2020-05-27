package com.javabyexamples.java.mapper.orika.optional;

import static org.assertj.core.api.Assertions.assertThat;

import com.javabyexamples.java.mapper.orika.generics.collections.Person;
import com.javabyexamples.java.mapper.orika.generics.collections.PersonDto;
import java.util.Optional;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.TypeFactory;
import org.junit.jupiter.api.Test;

public class OptionalTest {

    @Test
    public void testMappingOptional_UsingConverter() {
        final Person person = new Person();
        person.setName("Name");
        final Source source = new Source();
        source.setPerson(Optional.of(person));

        final Destination actual = getMapperFacade().map(source, Destination.class);

        assertThat(actual.getPerson().get().getName()).isEqualTo(person.getName());
    }

    @Test
    public void testMappingEmpty() {
        final Destination actual = getMapperFacade().map(new Source(), Destination.class);

        assertThat(actual.getPerson()).isNotPresent();
    }

    private MapperFacade getMapperFacade() {
        final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory()
          .registerConverter(
            new OptionalConverter<>(TypeFactory.valueOf(Person.class), TypeFactory.valueOf(PersonDto.class)));
        mapperFactory.classMap(Source.class, Destination.class).byDefault().register();
        return mapperFactory.getMapperFacade();
    }

    public static class Source {

        private Optional<Person> person = Optional.empty();

        public Optional<Person> getPerson() {
            return person;
        }

        public void setPerson(final Optional<Person> person) {
            this.person = person;
        }
    }

    public static class Destination {

        private Optional<PersonDto> person = Optional.empty();

        public Optional<PersonDto> getPerson() {
            return person;
        }

        public void setPerson(final Optional<PersonDto> person) {
            this.person = person;
        }
    }
}
