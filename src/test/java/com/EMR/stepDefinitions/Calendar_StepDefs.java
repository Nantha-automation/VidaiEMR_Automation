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

    @When("User books appointment for {string} tab from JSON test data with force booking option")
    public void user_books_appointment_for_tab_from_json_test_data_with_force_booking_option(String tabName) {
        String stepName = "User books appointment for " + tabName
                + " tab from JSON test data with force booking option";
        BrowserUtils.executeStep(stepName, () -> {
            AppointmentConfig config = Calendar.loadAppointmentByTabFromJson(tabName);
            calendar.forceBookAppointment(config);
        });
    }

    @When("User reschedules an existing appointment for {string} tab from JSON test data")
    public void user_reschedules_an_existing_appointment_for_tab_from_json_test_data(String tabName) {
        String stepName = "User reschedules an existing appointment for " + tabName + " tab from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            AppointmentConfig config = Calendar.loadAppointmentByTabFromJson(tabName);
            calendar.rescheduleAppointment(config);
        });
    }

    @Then("The appointment should be rescheduled successfully message should be displayed")
    public void the_appointment_should_be_rescheduled_successfully_message_should_be_displayed() {
        String stepName = "The appointment should be rescheduled successfully message should be displayed";
        BrowserUtils.executeStep(stepName, () -> {
            calendar.verifySuccessMessage("Appointment has been rescheduled");
        });
    }

    @When("User cancels an existing appointment for {string} tab from JSON test data")
    public void user_cancels_an_existing_appointment_for_tab_from_json_test_data(String tabName) {
        String stepName = "User cancels an existing appointment for " + tabName + " tab from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            AppointmentConfig config = Calendar.loadAppointmentByTabFromJson(tabName);
            calendar.cancelAppointment(config);
        });
    }

    @Then("The appointment should be cancelled successfully message should be displayed")
    public void the_appointment_should_be_cancelled_successfully_message_should_be_displayed() {
        String stepName = "The appointment should be cancelled successfully message should be displayed";
        BrowserUtils.executeStep(stepName, () -> {
            calendar.verifySuccessMessage("The appointment has been cancelled successfully.");
        });
    }

    @When("User books appointment for a new patient for {string} tab from JSON test data")
    public void user_books_appointment_for_a_new_patient_for_tab_from_json_test_data(String tabName) {
        String stepName = "User books appointment for a new patient for " + tabName + " tab from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            AppointmentConfig config = Calendar.loadAppointmentByTabFromJson(tabName);
            calendar.bookAppointmentForNewPatient(config);
        });
    }

    @Then("A success message should be displayed confirming that the appointment has been booked")
    public void a_success_message_should_be_displayed_confirming_that_the_appointment_has_been_booked() {
        String stepName = "A success message should be displayed confirming that the appointment has been booked";
        BrowserUtils.executeStep(stepName, () -> {
            calendar.verifySuccessMessage("Appointment booked successfully!");
        });
    }

    @Then("The user opens the newly booked appointment from {string} tab")
    public void the_user_opens_the_newly_booked_appointment_from_tab(String tabName) {
        String stepName = "The user opens the newly booked appointment from " + tabName + " tab";
        BrowserUtils.executeStep(stepName, () -> {
            AppointmentConfig config = Calendar.loadAppointmentByTabFromJson(tabName);
            calendar.clickAppointmentOnCurrentTab(config.patientName);
        });
    }

    @Then("Clicks on the Register button in the Patient information popup")
    public void clicks_on_the_register_button_in_the_patient_information_popup() {
        String stepName = "Clicks on the Register button in the Patient information popup";
        BrowserUtils.executeStep(stepName, () -> {
            calendar.clickRegisterButtonInPatientInformationPopup();
        });
    }

    @Then("Completes the registration for the new patient using JSON test data")
    public void completes_the_registration_for_the_new_patient_using_json_test_data() {
        String stepName = "Completes the registration for the new patient using JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            calendar.completeRegistrationForNewPatientUsingJsonData();
        });
    }

    @Then("A success message should be displayed confirming that the registration has been completed")
    public void a_success_message_should_be_displayed_confirming_that_the_registration_has_been_completed() {
        String stepName = "A success message should be displayed confirming that the registration has been completed";
        BrowserUtils.executeStep(stepName, () -> {
            calendar.verifySuccessMessage("Patient updated successfully");
        });
    }

    @Then("The user clicks on Exit Patient button from the sidebar")
    public void the_user_clicks_on_exit_patient_button_from_the_sidebar() {
        String stepName = "The user clicks on Exit Patient button from the sidebar";
        BrowserUtils.executeStep(stepName, () -> {
            calendar.clickExitPatientButtonFromSidebar();
        });
    }

    @Then("The user navigates back to the Calendar page")
    public void the_user_navigates_back_to_the_calendar_page() {
        String stepName = "The user navigates back to the Calendar page";
        BrowserUtils.executeStep(stepName, () -> {
            calendar.clickCalendarMenu();
        });
    }

    @And("Opens the newly booked appointment again using {string} tab")
    public void opens_the_newly_booked_appointment_again(String tabName) {
        String stepName = "Opens the newly booked appointment again using " + tabName + " tab";
        BrowserUtils.executeStep(stepName, () -> {
            AppointmentConfig config = Calendar.loadAppointmentByTabFromJson(tabName);
            calendar.clickAppointmentOnCurrentTab(config.patientName);
        });
    }

    @And("Clicks on the Check-In button in the patient information popup")
    public void clicks_on_the_check_in_button_in_the_patient_information_popup() {
        String stepName = "Clicks on the Check-In button in the patient information popup";
        BrowserUtils.executeStep(stepName, () -> {
            calendar.clickCheckInButtonInPatientInformationPopup();
        });
    }

    @Then("The patient status should be updated to {string} status in the patient information popup")
    public void the_patient_status_should_be_updated_to_status_in_the_patient_information_popup(String expectedStatus) {
        String stepName = "The patient status should be updated to " + expectedStatus
                + " status in the patient information popup";
        BrowserUtils.executeStep(stepName, () -> {
            calendar.verifyAppointmentStatus(expectedStatus);
        });
    }

    @And("User clicks on the Check-Out button in the patient information popup")
    public void user_clicks_on_the_check_out_button_in_the_patient_information_popup() {
        String stepName = "User clicks on the Check-Out button in the patient information popup";
        BrowserUtils.executeStep(stepName, () -> {
            calendar.clickCheckOutButtonInPatientInformationPopup();
        });
    }
}