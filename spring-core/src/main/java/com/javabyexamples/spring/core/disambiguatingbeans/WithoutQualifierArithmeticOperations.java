package com.javabyexamples.spring.core.disambiguatingbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WithoutQualifierArithmeticOperations {

    private final CalculationService calculationService;
    private final NumberService<Long> numberService;

    @Autowired
    public WithoutQualifierArithmeticOperations(CalculationService calculationService,
                                                NumberService<Long> numberService) {
        this.calculationService = calculationService;
        this.numberService = numberService;
    }

    public void start(){
        numberService.get();
        calculationService.calculate();
    }
}
