package com.javabyexamples.spring.core.disambiguatingbeans;

import org.springframework.stereotype.Component;

@Component("simple")
public class SimpleCalculationService implements CalculationService {

    @Override
    public void calculate() {
        System.out.println("Simple calculation");
    }
}
