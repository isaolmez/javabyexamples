package com.javabyexamples.java.core.tostring;

import com.google.common.base.MoreObjects;

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
    public String toString() {
        return MoreObjects.toStringHelper(this)
          .add("firstName", firstName)
          .add("lastName", lastName)
          .toString();
    }
}
