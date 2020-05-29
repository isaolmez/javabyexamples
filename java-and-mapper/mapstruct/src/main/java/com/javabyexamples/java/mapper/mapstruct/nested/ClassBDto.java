package com.javabyexamples.java.mapper.mapstruct.nested;

public class ClassBDto {

    private String propertyB;

    private ClassCDto child;

    public String getPropertyB() {
        return propertyB;
    }

    public void setPropertyB(String propertyB) {
        this.propertyB = propertyB;
    }

    public ClassCDto getChild() {
        return child;
    }

    public void setChild(ClassCDto child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "ClassBDto [propertyB=" + propertyB + ", child=" + child + "]";
    }
}
