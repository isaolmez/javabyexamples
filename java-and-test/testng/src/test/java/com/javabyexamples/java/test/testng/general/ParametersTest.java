package com.javabyexamples.java.test.testng.general;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParametersTest {

    @Test
    @Parameters("name")
    public void shouldRun(String name) {
        System.out.println("Running with parameter: " + name);
    }

}
