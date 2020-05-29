package com.javabyexamples.java.mapper.mapstruct.nested;

public class ClassCDto {

    private String propertyC;

    public String getPropertyC() {
        return propertyC;
    }

    public void setPropertyC(String propertyC) {
        this.propertyC = propertyC;
    }

    @Override
    public String toString() {
        return "ClassCDto{" +
          "propertyC='" + propertyC + '\'' +
          '}';
    }
}
