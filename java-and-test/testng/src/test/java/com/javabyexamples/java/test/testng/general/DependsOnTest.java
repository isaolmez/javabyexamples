package com.javabyexamples.java.test.testng.general;

import org.testng.annotations.Test;

public class DependsOnTest {

    @Test
    public void init() {
        System.out.println("Init");
    }

    @Test(dependsOnMethods = "init")
    public void shouldRunAfterInit() {
        System.out.println("Now I can run");
    }

}
