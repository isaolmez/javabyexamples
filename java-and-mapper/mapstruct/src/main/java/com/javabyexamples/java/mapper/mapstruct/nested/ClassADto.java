package com.javabyexamples.java.mapper.mapstruct.nested;

public class ClassADto {

    private String propertyA;

    private ClassBDto child;

    public String getPropertyA() {
        return propertyA;
    }

    public void setPropertyA(String propertyA) {
        this.propertyA = propertyA;
    }

    public ClassBDto getChild() {
        return child;
    }

    public void setChild(ClassBDto child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "ClassADto [propertyA=" + propertyA + ", child=" + child + "]";
    }
}
