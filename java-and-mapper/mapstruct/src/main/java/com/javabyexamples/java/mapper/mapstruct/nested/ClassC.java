package com.javabyexamples.java.mapper.mapstruct.nested;

public class ClassC {

    private String propertyC;

    public ClassC(){
    }

    public ClassC(String propertyC) {
        this.propertyC = propertyC;
    }

    public String getPropertyC() {
        return propertyC;
    }

    public void setPropertyC(String propertyC) {
        this.propertyC = propertyC;
    }

    @Override
    public String toString() {
        return "ClassC{" +
          "propertyC='" + propertyC + '\'' +
          '}';
    }
}
