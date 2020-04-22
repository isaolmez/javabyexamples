package com.javabyexamples.java.tools.lombok.annotations.nonnull.field;


public class CompanyDelomboked {

    private String location;

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        if (location == null) {
            throw new NullPointerException("location");
        }

        this.location = location;
    }
}
