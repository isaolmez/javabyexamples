package com.javabyexamples.spring.core.disambiguatingbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WithoutQualifierArithmeticOperations {

    private final CalculationService calculationService;

    @Autowired
    public WithoutQualifierArithmeticOperations(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    public void start() {
        calculationService.calculate();
    }
}
