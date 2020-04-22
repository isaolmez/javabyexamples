package com.javabyexamples.java.core.tostring;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

public class PersonUsingCommonsTest {

    @Test
    public void testToString_WhenUsingCommons() {
        final PersonUsingCommons person = new PersonUsingCommons();
        person.setFirstName("john");
        person.setLastName("doe");

        final String toString = person.toString();

        System.out.println(toString);
        assertThat(toString).containsPattern(
          "com.javabyexamples.java.core.tostring.PersonUsingCommons@.*[firstName=john,lastName=doe]");
    }

    @Test
    public void testToString_WhenUsingCommonsReflection() {
        final PersonUsingCommons person = getPerson();

        final String toString = ReflectionToStringBuilder.toString(person);

        System.out.println(toString);
        assertThat(toString).containsPattern(
          "com.javabyexamples.java.core.tostring.PersonUsingCommons@.*[firstName=john,lastName=doe]");
    }

    @Test
    public void testToString_WhenUsingCommons_DEFAULT_STYLE() {
        final String toString = executeToString(ToStringStyle.DEFAULT_STYLE);

        System.out.println(toString);
        assertThat(toString).containsPattern(
          "com.javabyexamples.java.core.tostring.PersonUsingCommons@.*[firstName=john,lastName=doe]");
    }

    @Test
    public void testToString_WhenUsingCommons_SHORT_PREFIX_STYLE() {
        final String toString = executeToString(ToStringStyle.SHORT_PREFIX_STYLE);

        System.out.println(toString);
        assertThat(toString).isEqualTo("PersonUsingCommons[firstName=john,lastName=doe]");
    }

    @Test
    public void testToString_WhenUsingCommons_NO_CLASS_NAME_STYLE() {
        final String toString = executeToString(ToStringStyle.NO_CLASS_NAME_STYLE);

        System.out.println(toString);
        assertThat(toString).isEqualTo("[firstName=john,lastName=doe]");
    }

    @Test
    public void testToString_WhenUsingCommons_NO_FIELD_NAMES_STYLE() {
        final String toString = executeToString(ToStringStyle.NO_FIELD_NAMES_STYLE);

        System.out.println(toString);
        assertThat(toString)
          .containsPattern("com.javabyexamples.java.core.tostring.PersonUsingCommons@.*[john,doe]");
    }

    @Test
    public void testToString_WhenUsingCommons_JSON_STYLE() {
        final String toString = executeToString(ToStringStyle.JSON_STYLE);

        System.out.println(toString);
        assertThat(toString).isEqualTo("{\"firstName\":\"john\",\"lastName\":\"doe\"}");
    }

    @Test
    public void testToString_WhenUsingCommons_SIMPLE_STYLE() {
        final String toString = executeToString(ToStringStyle.SIMPLE_STYLE);

        System.out.println(toString);
        assertThat(toString).isEqualTo("john,doe");
    }

    private String executeToString(ToStringStyle toStringStyle) {
        final PersonUsingCommons person = getPerson();

        return new ToStringBuilder(person, toStringStyle)
          .append("firstName", person.getFirstName())
          .append("lastName", person.getLastName())
          .build();
    }

    private PersonUsingCommons getPerson() {
        final PersonUsingCommons person = new PersonUsingCommons();
        person.setFirstName("john");
        person.setLastName("doe");
        return person;
    }
}
