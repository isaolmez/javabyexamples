package com.javabyexamples.spring.core.disambiguatingbeans;

public interface NumberService<T> {

    T get();
}
