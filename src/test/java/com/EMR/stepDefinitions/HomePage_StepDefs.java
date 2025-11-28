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
        BrowserUtils.click(homePage.close);
        String expectedText = JsonUtils.getValue("validLogin", "username");
        String actualText = homePage.loggedInUser.getText();
        // System.out.println(actualText);
        Assert.assertTrue("Expected text to contain username, but got: " + actualText, actualText.contains(expectedText));
    }

    @When("The user clicks Logout menu button")
    public void The_user_clicks_Logout_menu_button() {
        homePage.logout();
        
    }

}
