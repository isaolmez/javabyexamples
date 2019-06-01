package com.javabyexamples.spring.core.disambiguatingbeans;

public interface NumberService<T extends Number> {

    T get();
}
