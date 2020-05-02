package com.javabyexamples.java.test.cucumber.lesson3;

import com.javabyexamples.java.test.cucumber.common.CustomExpectedConditions;
import com.javabyexamples.java.test.cucumber.common.Constants.Facebook;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class Lesson3StepDefinitions {

    protected WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        System.out.printf("%s is starting%n", scenario.getName());
        driver = new FirefoxDriver();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Given("^I am on Facebook login page$")
    public void goToFacebookLogin() {
        driver.navigate().to(Facebook.LOGIN);
    }

    @When("^I enter username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void enterUsernameAndPassword(String username, String password) {
        driver.findElement(By.id(Facebook.USERNAME)).sendKeys(username);
        driver.findElement(By.id(Facebook.PASSWORD)).sendKeys(password);
        driver.findElement(By.cssSelector(Facebook.SUBMIT)).click();
    }

    @Then("^Login should succeed$")
    public void shouldLogin() {
        new FluentWait<>(driver)
          .withTimeout(5, TimeUnit.SECONDS)
          .pollingEvery(100, TimeUnit.MILLISECONDS)
          .until(ExpectedConditions.elementToBeClickable(By.cssSelector(Facebook.SUBMIT)));
        boolean attemptFailed = driver.getCurrentUrl().equalsIgnoreCase(Facebook.RE_LOGIN);
        System.out.println("attemptfailed:" + attemptFailed);
        if (attemptFailed) {
            Assert.fail();
        }
    }

    @Then("^Login should fail$")
    public void shouldFail() {
        CustomExpectedConditions.waitUntilSubmitIsFinished(driver);
        boolean attemptFailed = driver.getCurrentUrl().equalsIgnoreCase(Facebook.RE_LOGIN);
        System.out.println("attemptfailed:" + attemptFailed);
        if (!attemptFailed) {
            Assert.fail();
        }
    }
}
