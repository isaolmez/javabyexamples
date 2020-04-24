package com.javabyexamples.spring.core.usingbean.incomponent;

public class DefaultSampleRepository implements SampleRepository {

    @Override
    public void save() {
        System.out.println("Saving...");
    }
}
