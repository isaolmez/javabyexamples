package com.javabyexamples.java.mapper.mapstruct.nested;

public class ClassB {

    private String propertyB;

    private ClassC child;

    public ClassB() {
    }

    public ClassB(String propertyB, ClassC child) {
        this.propertyB = propertyB;
        this.child = child;
    }

    public String getPropertyB() {
        return propertyB;
    }

    public void setPropertyB(String propertyB) {
        this.propertyB = propertyB;
    }

    public ClassC getChild() {
        return child;
    }

    public void setChild(ClassC child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "ClassB{" +
          "propertyB='" + propertyB + '\'' +
          ", child=" + child +
          '}';
    }
}
