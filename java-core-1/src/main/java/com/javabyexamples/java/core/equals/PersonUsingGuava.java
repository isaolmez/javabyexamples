package com.javabyexamples.java.core.equals;

import com.google.common.base.Objects;

public class PersonUsingGuava {

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
        PersonUsingGuava that = (PersonUsingGuava) o;
        return Objects.equal(firstName, that.firstName) &&
          Objects.equal(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(firstName, lastName);
    }
}
