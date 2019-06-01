package com.javabyexamples.spring.core.usingautowired.wiremultiple;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ImportedGoodService implements GoodService {

    @Override
    public void buy() {
        System.out.println("Buying imported goods.");
    }
}
