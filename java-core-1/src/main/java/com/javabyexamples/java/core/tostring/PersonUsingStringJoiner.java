package com.javabyexamples.java.core.tostring;

import java.util.StringJoiner;

public class PersonUsingStringJoiner {

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
    public String toString() {
        return new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
          .add("firstName=" + firstName)
          .add("lastName=" + lastName)
          .toString();
    }
}
