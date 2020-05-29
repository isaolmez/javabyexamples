package com.javabyexamples.java.mapper.mapstruct.nested;

public class ClassA {

    private String propertyA;

    private ClassB child;

    public ClassA() {
    }

    public ClassA(String propertyA, ClassB child) {
        this.propertyA = propertyA;
        this.child = child;
    }

    public String getPropertyA() {
        return propertyA;
    }

    public void setPropertyA(String propertyA) {
        this.propertyA = propertyA;
    }

    public ClassB getChild() {
        return child;
    }

    public void setChild(ClassB child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "ClassA{" +
          "propertyA='" + propertyA + '\'' +
          ", child=" + child +
          '}';
    }
}
