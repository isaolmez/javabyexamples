package com.javabyexamples.java.test.cucumber.lesson4;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class LoggerHook {

    @Before
    public void setUp(Scenario scenario) {
        System.out.printf("\"%s\" is starting...%n", scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.printf("\"%s\" has finished...%n", scenario.getName());
    }
}
