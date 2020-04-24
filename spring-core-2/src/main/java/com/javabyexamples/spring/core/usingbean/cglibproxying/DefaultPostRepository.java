package com.javabyexamples.spring.core.usingbean.cglibproxying;

public class DefaultPostRepository implements PostRepository {

    @Override
    public void save() {
        System.out.println("Saving...");
    }
}
