package com.isa.java.test.cucumber.lesson5;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber"},
  features = "src/test/resources/com/isa/java/test/cucumber/lesson4/",
  glue = "com.isa.java.test.cucumber.lesson4")
public class Lesson5RunnerTest {
    // CucumberOptions usage
    // Test extension to be picked up by surefire plugin for unit tests
}
