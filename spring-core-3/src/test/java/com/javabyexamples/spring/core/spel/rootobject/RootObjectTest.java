package com.javabyexamples.spring.core.spel.rootobject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class RootObjectTest {

    private final ExpressionParser parser = new SpelExpressionParser();

    @Test
    public void shouldHandleRoot() {
        Person person = getPerson();

        Expression exp = parser.parseExpression("name+' '+lastName");
        String name = exp.getValue(person, String.class);

        assertThat(name).isEqualTo("John Doe");
    }

    @Test
    public void shouldHandleRootWithMethod() {
        Person person = getPerson();

        Expression exp = parser.parseExpression("name.concat(' ').concat(lastName)");
        String name = exp.getValue(person, String.class);

        assertThat(name).isEqualTo("John Doe");
    }

    private Person getPerson() {
        Person person = new Person();
        person.setName("John");
        person.setLastName("Doe");
        Address address = new Address();
        address.setStreet("Browns");
        address.setCity("London");
        address.setCountry("England");
        person.setAddress(address);
        return person;
    }
}
