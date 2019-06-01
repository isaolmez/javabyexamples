package com.javabyexamples.spring.core.disambiguatingbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class WithQualifierArithmeticOperations {

    private final CalculationService calculationService;

    @Autowired
    public WithQualifierArithmeticOperations(@Qualifier("simple") CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    public void start() {
        calculationService.calculate();
    }
}
