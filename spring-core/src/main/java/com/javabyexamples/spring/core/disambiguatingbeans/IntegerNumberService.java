package com.javabyexamples.spring.core.disambiguatingbeans;

public class IntegerNumberService implements NumberService<Integer> {

    @Override
    public Integer get() {
        System.out.println("Returning integer");
        return 1;
    }
}
