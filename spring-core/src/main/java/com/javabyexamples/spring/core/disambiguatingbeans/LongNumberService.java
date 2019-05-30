package com.javabyexamples.spring.core.disambiguatingbeans;

import org.springframework.stereotype.Component;

@Component
public class LongNumberService implements NumberService<Long> {

    @Override
    public Long get() {
        System.out.println("Returning long");
        return 1L;
    }
}
