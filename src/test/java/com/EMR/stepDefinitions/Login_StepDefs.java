package com.EMR.stepDefinitions;

import com.EMR.pages.Login;
import com.EMR.utilities.BrowserUtils;
import com.EMR.utilities.ConfigurationReader;
import com.EMR.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Login_StepDefs {
    Login loginPage = new Login();

    @Given("The user is on the Login page")
    public void the_user_is_on_the_login_page() {
        String stepName = "The user is on the Login page";
        BrowserUtils.executeStep(stepName, () -> {
            Driver.get().get(ConfigurationReader.get("url"));
            String expectedUrl = "https://staging-use1.vidaisolutions.com/login";
            Assert.assertEquals(Driver.get().getCurrentUrl(), expectedUrl);
        });
    }
    
    @When("The user enters correct username and password")
    public void the_user_enters_correct_username_and_password() {
        String stepName = "The user enters correct username and password";
        BrowserUtils.executeStep(stepName, () -> {
            loginPage.validLogin();
        });
    }

    @When("The user clicks login button")
    public void the_user_clicks_login_button() {
        String stepName = "The user clicks login button";
        BrowserUtils.executeStep(stepName, () -> {
            loginPage.loginButtonClick();
        });
    }

    @When("The user enters incorrect username and password")
    public void the_user_enters_incorrect_username_and_password() {
        String stepName = "The user enters incorrect username and password";
        BrowserUtils.executeStep(stepName, () -> {
            loginPage.inValidLogin();
        });
    }

    @Then("Verify error Invalid credentials is visible")
    public void verify_error_invalid_credentials_is_visible() {
        String stepName = "Verify error Invalid credentials is visible";
        BrowserUtils.executeStep(stepName, () -> {
            Assert.assertTrue("Error message not displayed for invalid login", loginPage.isErrorMessageDisplayed());
        });
    }

    @Then("Verify that user is navigated to login page")
    public void verify_that_user_is_navigated_to_login_page() {
        String stepName = "Verify that user is navigated to login page";
        BrowserUtils.executeStep(stepName, () -> {
            String expectedUrl = "https://staging-use1.vidaisolutions.com/login";
            String actualUrl = Driver.get().getCurrentUrl();
            Assert.assertEquals(expectedUrl, actualUrl);
        });
    }
}
