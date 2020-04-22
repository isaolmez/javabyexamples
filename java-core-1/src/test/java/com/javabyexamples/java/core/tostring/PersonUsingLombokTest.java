package com.javabyexamples.java.core.tostring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PersonUsingLombokTest {

    @Test
    public void testToString() {
        final PersonUsingLombok person = new PersonUsingLombok();
        person.setFirstName("john");
        person.setLastName("doe");

        final String toString = person.toString();

        System.out.println(toString);
        assertThat(toString).isEqualTo("PersonUsingLombok(firstName=john, lastName=doe)");
    }
}
