package com.javabyexamples.spring.core.usingbean.finalrule;

public class DefaultRepositoryOne implements RepositoryOne {

    @Override
    public void save() {
        System.out.println("Saving...");
    }
}
