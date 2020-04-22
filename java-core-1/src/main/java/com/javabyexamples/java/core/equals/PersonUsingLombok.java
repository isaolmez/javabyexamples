package com.javabyexamples.java.core.equals;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PersonUsingLombok {

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
}
