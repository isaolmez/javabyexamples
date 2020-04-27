package com.javabyexamples.spring.core.stereotypes.metaannotation;

import org.springframework.stereotype.Component;

@Component
public class SimpleGenerator implements Generator {

    @Override
    public void generate() {
        System.out.println("Simple generator");
    }
}
