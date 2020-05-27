package com.javabyexamples.java.mapper.orika.generics.basic;

import static org.assertj.core.api.Assertions.assertThat;

import com.javabyexamples.java.mapper.orika.generics.collections.Person;
import com.javabyexamples.java.mapper.orika.generics.collections.PersonDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeBuilder;
import org.junit.jupiter.api.Test;

public class GenericsBasicTest {

    @Test
    public void testParameterizedClass_UsingRawTypes() {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(Holder.class, AnotherHolder.class).byDefault().register();

        Person person = new Person();
        person.setName("Name");
        final Holder<Person> holder = new Holder<>();
        holder.setValue(person);

        final AnotherHolder<?> anotherHolder = factory.getMapperFacade().map(holder, AnotherHolder.class);

        assertThat(anotherHolder.getValue()).isNotInstanceOfAny(Person.class, PersonDto.class);
    }

    @Test
    public void testParameterizedCollection_UsingGenericTypes() {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        final Type<Holder<Person>> sourceType = new TypeBuilder<Holder<Person>>() {
        }.build();
        final Type<AnotherHolder<PersonDto>> targetType = new TypeBuilder<AnotherHolder<PersonDto>>() {
        }.build();
        factory.classMap(sourceType, targetType)
          .byDefault()
          .register();

        Person person = new Person();
        person.setName("Name");
        final Holder<Person> holder = new Holder<>();
        holder.setValue(person);

        final AnotherHolder<PersonDto> anotherHolder = factory.getMapperFacade().map(holder, sourceType, targetType);

        assertThat(anotherHolder.getValue().getName()).isEqualTo(holder.getValue().getName());
    }

    @Test
    public void testActualizedClass_UsingRawTypes() {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(PersonHolder.class, AnotherPersonHolder.class).byDefault().register();

        Person person = new Person();
        person.setName("Name");
        final PersonHolder personHolder = new PersonHolder();
        personHolder.setValue(person);

        final AnotherPersonHolder anotherPersonHolder = factory.getMapperFacade()
          .map(personHolder, AnotherPersonHolder.class);

        assertThat(anotherPersonHolder.getValue().getName()).isEqualTo(personHolder.getValue().getName());
    }
}
