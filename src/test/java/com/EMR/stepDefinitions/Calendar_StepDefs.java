package com.EMR.stepDefinitions;

import java.util.List;

import com.EMR.pages.Calendar;
import com.EMR.pages.Calendar.AppointmentConfig;
import com.EMR.utilities.BrowserUtils;
import com.EMR.utilities.JsonUtils;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;

public class Calendar_StepDefs {
    Calendar calendar = new Calendar();

    @Then("Verify that Logged in as username is visible")
    public void verify_that_logged_in_as_username_is_visible() {
        String stepName = "Verify that Logged in as username is visible";
        BrowserUtils.executeStep(stepName, () -> {
            BrowserUtils.click(calendar.close);
            String expectedText = JsonUtils.getValue("validLogin", "username");
            String actualText = calendar.loggedInUser.getText();
            Assert.assertTrue("Expected text to contain username as Expected username, but got: " + actualText,
                    actualText.contains(expectedText));
        });
    }

    @When("The user clicks Logout menu button")
    public void The_user_clicks_Logout_menu_button() {
        String stepName = "The user clicks Logout menu button";
        BrowserUtils.executeStep(stepName, () -> {
            calendar.logout();
        });
    }

    @Given("User is on the Calendar Page")
    public void user_is_on_the_calendar_page() {
        String stepName = "User is on the Calendar Page";
        BrowserUtils.executeStep(stepName, () -> {
            calendar.clickCalendarMenu();
        });
    }

    @Then("All required UI elements should be displayed on the Calendar Page")
    public void all_required_ui_elements_should_be_displayed_on_the_calendar_page() {
        String stepName = "All required UI elements should be displayed on the Calendar Page";
        BrowserUtils.executeStep(stepName, () -> {
            calendar.verifyUiElements();
        });
    }

    @Then("The Admin_Clinic_Month_MonthNameAndYear should be selected by default")
    public void the_admin_clinic_month_month_name_and_year_should_be_selected_by_default() {
        String stepName = "The Admin_Clinic_Month_MonthNameAndYear should be selected by default";
        BrowserUtils.executeStep(stepName, () -> {
            calendar.verifyDefaultSelections();
        });
    }

    @When("User books appointment for {string} tab from JSON test data")
    public void user_books_appointment_for_tab_from_json_test_data(String tabName) {
        String stepName = "User books appointment for " + tabName + " tab from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            AppointmentConfig config = Calendar.loadAppointmentByTabFromJson(tabName);
            List<AppointmentConfig> configs = java.util.Arrays.asList(config);
            calendar.bookAppointmentsForAllTabs(configs);
        });
    }

}
