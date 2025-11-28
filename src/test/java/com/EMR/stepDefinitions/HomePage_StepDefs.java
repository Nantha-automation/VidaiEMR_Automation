package com.EMR.stepDefinitions;

import com.EMR.pages.HomePage;
import com.EMR.utilities.BrowserUtils;
import com.EMR.utilities.JsonUtils;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;

public class HomePage_StepDefs {
    HomePage homePage = new HomePage();
    

    @Then("Verify that Logged in as username is visible")
    public void verify_that_logged_in_as_username_is_visible() {
        String stepName = "Verify that Logged in as username is visible";
        BrowserUtils.executeStep(stepName, () -> {
            BrowserUtils.click(homePage.close);
            String expectedText = JsonUtils.getValue("validLogin", "username");
            String actualText = homePage.loggedInUser.getText();
            Assert.assertTrue("Expected text to contain username as Expected username, but got: " + actualText, actualText.contains(expectedText));
        });
    }

    @When("The user clicks Logout menu button")
    public void The_user_clicks_Logout_menu_button() {
        String stepName = "The user clicks Logout menu button";
        BrowserUtils.executeStep(stepName, () -> {
            homePage.logout();
        });
    }

}
