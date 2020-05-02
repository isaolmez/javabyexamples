package com.isa.java.test.cucumber.lesson1;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber"})
public class Lesson1Runner {
    // Hooks usage
    // and But usage
    // and others...
}