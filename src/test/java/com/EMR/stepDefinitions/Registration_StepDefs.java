package com.EMR.stepDefinitions;

import com.EMR.pages.Registration;
import com.EMR.utilities.BrowserUtils;
import com.EMR.utilities.JsonUtils;
import com.EMR.utilities.ScenarioContext;
import com.fasterxml.jackson.databind.JsonNode;

import io.cucumber.java.en.And;
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

    @And("SearchBy radio options should be present as per json test data")
    public void searchby_radio_options_should_be_present_as_per_json_test_data() {
        String stepName = "SearchBy radio options should be present as per json test data";
        BrowserUtils.executeStep(stepName, () -> {
            registration.verifyRadioButton();
        });
    }

    @And("User clicks the New Registration button")
    public void user_clicks_the_new_registration_button() {
        String stepName = "User clicks the New Registration button";
        BrowserUtils.executeStep(stepName, () -> {
            registration.openNewRegistrationForm();
        });
    }

    @When("User enters patient details from JSON test data")
    public void user_enters_patient_details_from_json_test_data() {
        String stepName = "User enters patient details from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            registration.fillPatientInformationFromJson();
        });
    }

    @And("User clicks the Save and Next button")
    public void user_clicks_the_save_and_next_button() {
        String stepName = "User clicks the Save and Next button";
        BrowserUtils.executeStep(stepName, () -> {
            registration.saveAndNext();
        });
    }

    @And("User enters partner details from JSON test data")
    public void user_enters_partner_details_from_json_test_data() {
        String stepName = "User enters partner details from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            registration.fillPartnerInformationFromJson();
        });
    }

    @And("User clicks the Register button on the Patient_Partner Details page")
    public void user_clicks_the_register_button_on_the_patient_partner_details_page() {
        String stepName = "User clicks the Register button on the Patient_Partner Details page";
        BrowserUtils.executeStep(stepName, () -> {
            registration.register();
        });
    }

    @And("User confirms the registration by clicking Yes on the confirmation popup")
    public void user_confirms_registration_by_clicking_yes_on_confirmation_popup() {
        String stepName = "User confirms the registration by clicking Yes on the confirmation popup";
        BrowserUtils.executeStep(stepName, () -> {
            registration.confirmRegistrationYes();
        });
    }

    @Then("The patient registered successfully message should be displayed from {string} JSON node")
    public void the_patient_registered_successfully_message_should_be_displayed(String infoKey) {
        String stepName = "The patient registered successfully message should be displayed from " + infoKey
                + " JSON node";
        BrowserUtils.executeStep(stepName, () -> {

            // registration.newRegistration.<infoKey>.successMessage
            JsonNode info = JsonUtils.getNestedNode("registration", "newRegistration", infoKey);

            String expected = "registered successfully."; // default fallback

            if (info != null && info.hasNonNull("successMessage")) {
                expected = info.get("successMessage").asText();
            }

            registration.verifySuccessLabel(expected);
        });
    }

    @And("User searches newly registered patient from {string} JSON node")
    public void user_searches_newly_registered_patient_from_json_node(String infoKey) {
        String stepName = "User searches newly registered patient from \"" + infoKey + "\" JSON node";
        BrowserUtils.executeStep(stepName, () -> {
            registration.searchRegisteredPatientFromJsonAndVerify(infoKey);
        });
    }

    @When("User enters patient-single details from JSON test data")
    public void user_enters_patient_single_details_from_json_test_data() {
        String stepName = "User enters patient-single details from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            registration.fillPatientSingleInformationFromJson();
        });
    }

    @When("User enters SPERM_DONOR patient details from JSON test data")
    public void user_enters_sperm_donor_patient_details_from_json_test_data() {
        String stepName = "User enters SPERM_DONOR patient details from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            registration.fillSpermDonorInformationFromJson();
        });
    }

    @Then("The donor registered successfully message should be displayed")
    public void the_donor_registered_successfully_message_should_be_displayed() {
        String stepName = "The donor registered successfully message should be displayed";
        BrowserUtils.executeStep(stepName, () -> {
            // Get successMessage from
            // registration.newRegistration.partnerInformation.successMessage
            com.fasterxml.jackson.databind.JsonNode donorInfo = JsonUtils.getNestedNode("registration",
                    "newRegistration", "sperm_donorInformation");
            String expected = "donor information registration successful!"; // default
            if (donorInfo != null && donorInfo.has("successMessage")) {
                expected = donorInfo.get("successMessage").asText();
            }
            registration.verifySuccessLabel(expected);
        });
    }

    @And("User clicks the Exit Patient button from the sidebar")
    public void user_clicks_the_exit_patient_button_from_the_sidebar() {
        String stepName = "User clicks the Exit Patient button from the sidebar";
        BrowserUtils.executeStep(stepName, () -> {
            registration.clickExitPatient();
        });
    }

    @And("User clicks on {string} radio button on the Registration Page")
    public void user_clicks_on_the_radio_button_on_the_registration_page(String radioButton) {
        String stepName = "User clicks on " + radioButton + " Radio button on the Registration Page";
        BrowserUtils.executeStep(stepName, () -> {
            registration.selectSearchByRadioOptionUsingTag(ScenarioContext.getScenario());
        });
    }

    @When("User enters EGG_DONOR patient details from JSON test data")
    public void user_enters_egg_donor_patient_details_from_json_test_data() {
        String stepName = "User enters EGG_DONOR patient details from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            registration.fillEggDonorInformationFromJson();
        });
    }

    @And("User selects the {string} registration type from JSON test data")
    public void user_selects_registration_type_from_json_test_data(String type) {
        String stepName = "User selects the " + type + " registration type from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            registration.selectRegistrationType(type);
        });
    }

    @When("User enters SURROGATE patient details from JSON test data")
    public void user_enters_surrogate_patient_details_from_json_test_data() {
        String stepName = "User enters SURROGATE patient details from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            registration.fillSurrogateInformationFromJson();
        });
    }

}