package com.javabyexamples.java.core.tostring;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.base.MoreObjects;
import org.junit.Test;

public class PersonUsingGuavaTest {

    @Test
    public void testToString() {
        final PersonUsingGuava person = new PersonUsingGuava();
        person.setFirstName("john");
        person.setLastName("doe");

        final String toString = person.toString();

        System.out.println(toString);
        assertThat(toString).isEqualTo("PersonUsingGuava{firstName=john, lastName=doe}");
    }

    @Test
    public void testToString_WhenOmittingNullValues() {
        final PersonUsingGuava person = new PersonUsingGuava();
        person.setFirstName("john");
        person.setLastName(null);

        final String toString = MoreObjects.toStringHelper(person)
          .add("firstName", person.getFirstName())
          .add("lastName", person.getLastName())
          .omitNullValues()
          .toString();

        System.out.println(toString);
        assertThat(toString).isEqualTo("PersonUsingGuava{firstName=john}");
    }
}
