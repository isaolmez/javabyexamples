package com.isa.java.test.cucumber.lesson2;

import static com.isa.java.test.cucumber.common.Constants.Facebook.LOGIN;
import static com.isa.java.test.cucumber.common.Constants.Facebook.PASSWORD;
import static com.isa.java.test.cucumber.common.Constants.Facebook.RE_LOGIN;
import static com.isa.java.test.cucumber.common.Constants.Facebook.SUBMIT;
import static com.isa.java.test.cucumber.common.Constants.Facebook.USERNAME;
import static org.junit.Assert.fail;

import com.isa.java.test.cucumber.common.CustomExpectedConditions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Lesson2StepDefinitions {

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
    public void goToFacebook() {
        driver.navigate().to(LOGIN);
    }

    @When("^I enter username as \"(.*)\" and password as \"(.*)\"$")
    public void enterUsernameAndPassword(String username, String password) {
        driver.findElement(By.id(USERNAME)).sendKeys(username);
        driver.findElement(By.id(PASSWORD)).sendKeys(password);
        driver.findElement(By.cssSelector(SUBMIT)).click();
    }

    @Then("^Login should fail$")
    public void checkFail() {
        CustomExpectedConditions.waitUntilSubmitIsFinished(driver);
        if (driver.getCurrentUrl().equalsIgnoreCase(RE_LOGIN)) {
            System.out.println("Test1 Pass");
        } else {
            System.out.println("Test1 Failed");
            fail();
        }
    }
}
