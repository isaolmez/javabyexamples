package com.javabyexamples.spring.core.disambiguatingbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class WithQualifierArithmeticOperations {

    private final CalculationService calculationService;
    private final NumberService<Long> numberService;

    @Autowired
    public WithQualifierArithmeticOperations(@Qualifier("simple") CalculationService calculationService,
                                             @Qualifier("longNumberService") NumberService numberService) {
        this.calculationService = calculationService;
        this.numberService = numberService;
    }

    public void start(){
        numberService.get();
        calculationService.calculate();
    }
}
