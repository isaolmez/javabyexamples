package com.javabyexamples.java.core.equals;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PersonUsingCommons {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonUsingCommons that = (PersonUsingCommons) o;

        return new EqualsBuilder()
          .append(firstName, that.firstName)
          .append(lastName, that.lastName)
          .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
          .append(firstName)
          .append(lastName)
          .toHashCode();
    }
}
