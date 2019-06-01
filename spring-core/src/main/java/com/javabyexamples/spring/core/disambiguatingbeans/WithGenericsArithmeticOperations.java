package com.javabyexamples.spring.core.disambiguatingbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WithGenericsArithmeticOperations {

    private final NumberService<Long> numberService;

    @Autowired
    public WithGenericsArithmeticOperations(NumberService<Long> numberService) {
        this.numberService = numberService;
    }

    public void start() {
        numberService.get();
    }
}
