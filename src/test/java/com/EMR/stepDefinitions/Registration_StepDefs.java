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

    @When("User clicks the Save and Next button without entering any details")
    public void user_clicks_the_save_and_next_button_without_entering_any_details() {
        String stepName = "User clicks the Save and Next button without entering any details";
        BrowserUtils.executeStep(stepName, () -> {
            registration.saveAndNext();
        });
    }

    @Then("The failure message should be displayed at the top of the Registration Page")
    public void the_failure_message_should_be_displayed_at_the_top_of_the_registration_page() {
        String stepName = "The failure message should be displayed at the top of the Registration Page";
        BrowserUtils.executeStep(stepName, () -> {
            registration.verifyFailureMessage();
        });
    }

    @And("User clicks the Cancel button")
    public void user_clicks_the_cancel_button() {
        String stepName = "User clicks the Cancel button";
        BrowserUtils.executeStep(stepName, () -> {
            registration.cancel();
        });
    }

    @And("User confirms the cancellation by clicking Yes on the confirmation popup")
    public void user_confirms_the_cancellation_by_clicking_yes_on_the_confirmation_popup() {
        String stepName = "User confirms the cancellation by clicking Yes on the confirmation popup";
        BrowserUtils.executeStep(stepName, () -> {
            registration.confirmRegistrationYes();
        });
    }

    @Then("User should be navigated back to the Registration Page")
    public void user_should_be_navigated_back_to_the_registration_page() {
        String stepName = "User should be navigated back to the Registration Page";
        BrowserUtils.executeStep(stepName, () -> {
            registration.verifyRegistrationPage();
        });
    }

    @When("User searches from already registered partner to link as Partner")
    public void user_searches_from_already_registered_partner_to_link_as_partner() {
        String stepName = "User searches from already registered partner to link as Partner";
        BrowserUtils.executeStep(stepName, () -> {
            registration.searchPartnerToLink();
        });
    }

    @And("User selects the partner from the search results")
    public void user_selects_the_partner_from_the_search_results() {
        String stepName = "User selects the partner from the search results";
        BrowserUtils.executeStep(stepName, () -> {
            registration.selectPartnerFromSearchResults();
        });
    }

    @When("User clicks on Link Partner button")
    public void user_clicks_on_link_partner_button() {
        String stepName = "User clicks on Link Partner button";
        BrowserUtils.executeStep(stepName, () -> {
            registration.clickLinkPartnerButton();
        });
    }

    @Then("The partner linked successfully message should be displayed")
    public void the_partner_linked_successfully_message_should_be_displayed() {
        String stepName = "The partner linked successfully message should be displayed";
        BrowserUtils.executeStep(stepName, () -> {
            registration.verifyPartnerLinkedSuccessMessage();
        });
    }

    @And("User clicks on the {string} Partner Icon")
    public void user_clicks_on_the_partner_button(String actionType) {
        String stepName = "User clicks on the " + actionType + " Partner Icon";
        BrowserUtils.executeStep(stepName, () -> {
            registration.clickPartnerLinkAndDelinkIcon(actionType);
        });
    }

    @And("User fills the Delink Reason and Remark from JSON test data")
    public void user_fills_the_delink_reason_and_remark_from_json_test_data() {
        String stepName = "User fills the Delink Reason and Remark from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            registration.fillDelinkReasonAndRemark();
        });
    }

    @When("User clicks on Delink Partner button")
    public void user_clicks_on_delink_partner_button() {
        String stepName = "User clicks on Delink Partner button";
        BrowserUtils.executeStep(stepName, () -> {
            registration.clickDelinkPartnerButton();
        });
    }

    @Then("User confirms the {string} action by clicking Yes on the confirmation popup")
    public void user_confirms_the_action_by_clicking_yes_on_the_confirmation_popup(String actionType) {
        String stepName = "User confirms the " + actionType + " action by clicking Yes on the confirmation popup";
        BrowserUtils.executeStep(stepName, () -> {
            // same Yes button for both Link & Delink
            registration.confirmRegistrationYes();
        });
    }

    @Then("The partner delinked successfully message should be displayed")
    public void the_partner_delinked_successfully_message_should_be_displayed() {
        String stepName = "The partner delinked successfully message should be displayed";
        BrowserUtils.executeStep(stepName, () -> {
            registration.verifyPartnerDelinkedSuccessMessage();
        });
    }

    @When("User clicks on the Patient Name in the Patient List Table")
    public void user_clicks_on_the_patient_name_in_the_patient_list_table() {
        String stepName = "User clicks on the Patient Name in the Patient List Table";
        BrowserUtils.executeStep(stepName, () -> {
            registration.clickPatientNameInTable();
        });
    }

    @And("User clicks on the Edit button on the Patient Profile page")
    public void user_clicks_on_the_edit_button_on_the_patient_profile_page() {
        String stepName = "User clicks on the Edit button on the Patient Profile page";
        BrowserUtils.executeStep(stepName, () -> {
            registration.clickEditButtonOnPatientProfile();
        });
    }

    @And("User modifies patient Reason Of Visit details from JSON test data")
    public void user_modifies_patient_reason_of_visit_details_from_json_test_data() {
        String stepName = "User modifies patient Reason Of Visit details from JSON test data";
        BrowserUtils.executeStep(stepName, () -> {
            registration.modifyReasonOfVisitFromJson();
        });
    }

    @And("User clicks the Update Patient button")
    public void user_clicks_the_update_patient_button() {
        String stepName = "User clicks the Update Patient button";
        BrowserUtils.executeStep(stepName, () -> {
            registration.clickUpdatePatientButton();
        });
    }

    @And("User confirms the update by clicking Yes on the confirmation popup")
    public void user_confirms_the_update_by_clicking_yes_on_the_confirmation_popup() {
        String stepName = "User confirms the update by clicking Yes on the confirmation popup";
        BrowserUtils.executeStep(stepName, () -> {
            registration.confirmRegistrationYes();
        });
    }

    @Then("The patient updated successfully message should be displayed")
    public void the_patient_updated_successfully_message_should_be_displayed() {
        String stepName = "The patient updated successfully message should be displayed";
        BrowserUtils.executeStep(stepName, () -> {
            registration.verifyPatientUpdatedSuccessMessage();
        });
    }
}