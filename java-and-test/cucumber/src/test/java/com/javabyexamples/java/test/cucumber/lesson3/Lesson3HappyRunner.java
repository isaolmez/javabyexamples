package com.javabyexamples.java.test.cucumber.lesson3;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber"}, tags = {"@HappyPath"})
public class Lesson3HappyRunner {
    // Tag usage

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
    }
}
