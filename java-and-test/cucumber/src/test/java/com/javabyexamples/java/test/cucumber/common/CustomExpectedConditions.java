package com.javabyexamples.java.test.cucumber.common;

import static com.javabyexamples.java.test.cucumber.common.Constants.Facebook.SUBMIT;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class CustomExpectedConditions {

    public static void waitUntilSubmitIsFinished(WebDriver driver) {
        new FluentWait<>(driver)
          .withTimeout(15, TimeUnit.SECONDS)
          .pollingEvery(100, TimeUnit.MILLISECONDS)
          .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(SUBMIT)));
    }
}
