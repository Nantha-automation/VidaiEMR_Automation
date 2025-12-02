package com.EMR.stepDefinitions;

import com.EMR.pages.Registration;
import com.EMR.utilities.BrowserUtils;
import com.EMR.utilities.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Registration_StepDefs {

    Registration registration = new Registration();

    @Given("User is on the Registration Page")
    public void user_is_on_the_registration_page() {
        String stepName = "User is on the Registration Page";
        BrowserUtils.executeStep(stepName, () -> {
            registration.clickRegistrationMenu();
        });
    }

    @Then("All required UI elements should be displayed")
    public void all_required_ui_elements_should_be_displayed() {
        String stepName = "All required UI elements should be displayed";
        BrowserUtils.executeStep(stepName, () -> {
            registration.verifyUiElements();
        });
    }

    @Then("SearchBy radio options should be present as per json test data")
    public void searchby_radio_options_should_be_present_as_per_json_test_data() {
        String stepName = "SearchBy radio options should be present as per json test data";
        BrowserUtils.executeStep(stepName, () -> {
            registration.verifyRadioButton();
        });
    }

    @Given("User clicks the New Registration button")
    public void user_clicks_the_new_registration_button() {
        String stepName = "User clicks the New Registration button";
        BrowserUtils.executeStep(stepName, () -> {
            registration.openNewRegistrationForm();
        });
    }

    @Given("User selects the Patient-Couple registration type from JSON test data")
    public void user_selects_the_patient_couple_registration_type_from_json_test_data() {
        String stepName = "User selects the Patient-Couple registration type from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            // Get the first registration type from the array: PATIENT_COUPLE
            String type = "PATIENT_COUPLE"; // Default from JSON array
            registration.selectRegistrationType(type);
        });
    }

    @When("User enters patient details from JSON test data")
    public void user_enters_patient_details_from_json_test_data() {
        String stepName = "User enters patient details from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            registration.fillPatientInformationFromJson();
        });
    }

    @When("User clicks the Save and Next button")
    public void user_clicks_the_save_and_next_button() {
        String stepName = "User clicks the Save and Next button";
        BrowserUtils.executeStep(stepName, () -> {
            registration.saveAndNext();
        });
    }

    @When("User enters partner details from JSON test data")
    public void user_enters_partner_details_from_json_test_data() {
        String stepName = "User enters partner details from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            registration.fillPartnerInformationFromJson();
        });
    }

    @When("User clicks the Register button on the Patient_Partner Details page")
    public void user_clicks_the_register_button_on_the_patient_partner_details_page() {
        String stepName = "User clicks the Register button on the Patient_Partner Details page";
        BrowserUtils.executeStep(stepName, () -> {
            registration.register();
        });
    }

    @When("User confirms the registration by clicking Yes on the confirmation popup")
    public void user_confirms_registration_by_clicking_yes_on_confirmation_popup() {
        String stepName = "User confirms the registration by clicking Yes on the confirmation popup";
        BrowserUtils.executeStep(stepName, () -> {
            registration.confirmRegistrationYes();
        });
    }

    @Then("The patient registered successfully message should be displayed")
    public void the_patient_registered_successfully_message_should_be_displayed() {
        String stepName = "The patient registered successfully message should be displayed";
        BrowserUtils.executeStep(stepName, () -> {
            // Get successMessage from registration.newRegistration.partnerInformation.successMessage
            com.fasterxml.jackson.databind.JsonNode partnerInfo = JsonUtils.getNestedNode("registration", "newRegistration", "partnerInformation");
            String expected = "Patient registered successfully."; // default
            if (partnerInfo != null && partnerInfo.has("successMessage")) {
                expected = partnerInfo.get("successMessage").asText();
            }
            registration.verifySuccessLabel(expected);
        });
    }

    @Then("User searches for the newly registered patient by name from JSON test data")
    public void user_searches_for_the_newly_registered_patient_by_name_from_json_test_data() {
        String stepName = "User searches for the newly registered patient by name from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            registration.searchRegisteredPatientFromJsonAndVerify();
        });
    }

    @Given("User selects the Patient-Single registration type from JSON test data")
    public void user_selects_the_patient_single_registration_type_from_json_test_data() {
        String stepName = "User selects the Patient-Single registration type from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            // Get the first registration type from the array: PATIENT_COUPLE
            String type = "PATIENT_SINGLE"; // Default from JSON array
            registration.selectRegistrationType(type);
        });
    }
}
