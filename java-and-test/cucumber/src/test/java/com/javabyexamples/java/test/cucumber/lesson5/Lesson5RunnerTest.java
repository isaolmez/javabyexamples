package com.javabyexamples.java.test.cucumber.lesson5;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber"},
  features = "src/test/resources/com/javabyexamples/java/test/cucumber/lesson4/",
  glue = "com.javabyexamples.java.test.cucumber.lesson4")
public class Lesson5RunnerTest {
    // CucumberOptions usage
    // Test extension to be picked up by surefire plugin for unit tests

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
    }
}
