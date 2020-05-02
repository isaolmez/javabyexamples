package com.isa.java.test.cucumber.lesson4;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber"})
public class Lesson4Runner {
    // Hooks are picked up automatically
}