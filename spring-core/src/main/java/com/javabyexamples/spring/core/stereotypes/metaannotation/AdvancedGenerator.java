package com.javabyexamples.spring.core.stereotypes.metaannotation;

@PrimaryComponent
public class AdvancedGenerator implements Generator{

    @Override
    public void generate() {
        System.out.println("Advanced generator");
    }
}
