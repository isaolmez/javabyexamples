package com.javabyexamples.spring.core.disambiguatingbeans;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class AdvancedCalculationService implements CalculationService {

    @Override
    public void calculate() {
        System.out.println("Advanced calculation");
    }
}
