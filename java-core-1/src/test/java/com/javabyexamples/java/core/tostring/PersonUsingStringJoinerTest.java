package com.javabyexamples.java.core.tostring;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

public class PersonUsingStringJoinerTest {

    @Test
    public void testToString() {
        final PersonUsingStringJoiner person = new PersonUsingStringJoiner();
        person.setFirstName("john");
        person.setLastName("doe");

        final String toString = person.toString();

        System.out.println(toString);
        assertThat(toString).isEqualTo("PersonUsingStringJoiner[firstName=john, lastName=doe]");
    }
}
