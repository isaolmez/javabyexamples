package com.javabyexamples.java.mapper.orika.generics.basic;

public class Holder<T> {

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
