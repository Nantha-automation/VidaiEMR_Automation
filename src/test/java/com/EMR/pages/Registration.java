package com.EMR.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.EMR.utilities.BrowserUtils;
import com.EMR.utilities.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.EMR.utilities.Driver;
import org.openqa.selenium.By;

public class Registration extends BasePage {

    @FindBy(xpath = "//li[text()='Registration']")
    private WebElement registrationMenu;

    @FindBy(xpath = "//input[@type='radio']")
    public List<WebElement> searchByRadios;

    @FindBy(id = "searchInput")
    public WebElement searchPatient;

    @FindBy(id = "start-date")
    public WebElement startDate;

    @FindBy(id = "end-date")
    public WebElement endDate;

    @FindBy(xpath = "//*[name()='svg' and @data-testid='LinkIcon']")
    public WebElement linkPartner;

    @FindBy(xpath = "//*[name()='svg' and @data-testid='LinkOffIcon']")
    public WebElement unLinkPartner;

    @FindBy(xpath = "//span[text()='New Registration']")
    public WebElement newRegistrationButton;

    @FindBy(id = "prefix-select")
    public WebElement newRegistrationPrefix;

    @FindBy(id = "firstName")
    public WebElement newRegistrationFirstName;

    @FindBy(id = "middleName")
    public WebElement newRegistrationMiddleName;

    @FindBy(id = "lastName")
    public WebElement newRegistrationLastName;

    @FindBy(id = "sex-assigned-select")
    public WebElement newRegistrationSexAssigned;

    @FindBy(id = "demo-simple-select")
    public WebElement newRegistrationBloodGroup;

    @FindBy(id = "demo-simple-select-marital")
    public WebElement newRegistrationMaritalStatus;

    @FindBy(id = "outlined-id-type")
    public WebElement newRegistrationIdType;

    @FindBy(id = "outlined-id")
    public WebElement newRegistrationId;

    @FindBy(id = "reference-source-select")
    public WebElement newRegistrationReferenceSource;

    @FindBy(id = "additional-details")
    public WebElement newRegistrationAdditionalDetails;

    @FindBy(id = "occupation-industry-autocomplete")
    public WebElement newRegistrationOccupationIndustry;

    @FindBy(id = "reason-of-visit")
    public WebElement newRegistrationReasonOfVisit;

    @FindBy(id = "ethnicity-select")
    public WebElement newRegistrationEthnicity;

    @FindBy(id = "preferred-language-select")
    public WebElement newRegistrationPreferredLanguage;

    @FindBy(xpath = "(//label[text()='Country Code'])[1]")
    public WebElement newRegistrationCountryCodeMobile;

    @FindBy(id = "mobile-number")
    public WebElement newRegistrationMobileNumber;

    @FindBy(xpath = "(//label[text()='Country Code'])[2]")
    public WebElement newRegistrationCountryCodeOffice;

    @FindBy(id = "office-number")
    public WebElement newRegistrationOfficeNumber;

    @FindBy(id = "email-id")
    public WebElement newRegistrationEmailId;

    @FindBy(id = "address")
    public WebElement newRegistrationAddress;

    @FindBy(xpath = "//input[@id=':r9a:']")
    public WebElement newRegistrationCountry;

    @FindBy(xpath = "//input[@id=':r9d:']")
    public WebElement newRegistrationState;

    @FindBy(xpath = "//input[@id=':r9g:']")
    public WebElement newRegistrationCity;

    @FindBy(id = "area-code")
    public WebElement newRegistrationAreaCode;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    public WebElement newRegistrationCancelButton;

    @FindBy(xpath = "//button[normalize-space()='Save & Next']")
    public WebElement newRegistrationSaveAndNextButton;

    @FindBy(xpath = "//button[normalize-space()='Register']")
    public WebElement registerButton;

    public void clickRegistrationMenu() {
        BrowserUtils.click(registrationMenu);
    }

    public void selectSearchByOption(String value) {
        BrowserUtils.selectRadioByValue(searchByRadios, value);
    }

    public List<String> getSearchByValues() {
        return searchByRadios.stream().map(e -> e.getAttribute("value")).collect(Collectors.toList());
    }

    public void verifyUiElements() {
        BrowserUtils.verifyElementDisplayed(searchPatient);
        BrowserUtils.verifyElementDisplayed(startDate);
        BrowserUtils.verifyElementDisplayed(endDate);
        BrowserUtils.verifyElementDisplayed(newRegistrationButton);
        // At least one radio
        Assert.assertTrue("SearchBy radios should be present", !searchByRadios.isEmpty());
    }

    public void verifyRadioButton() {
        List<String> expected = JsonUtils.getArrayValues("registration", "searchBy");
        List<String> actual = getSearchByValues();
        // Normalize values to ignore case
        Assert.assertTrue("Mismatch in SearchBy radios. Expected: " + expected + " but got: " + actual,
                actual.stream().map(String::toUpperCase).collect(Collectors.toList()).containsAll(
                        expected.stream().map(String::toUpperCase).collect(Collectors.toList())));
    }

    // --- New Registration workflow actions ---
    public void openNewRegistrationForm() {
        BrowserUtils.click(newRegistrationButton);
        BrowserUtils.waitForPageToLoad(10);
    }

    public void selectRegistrationType(String type) {
        // Map internal values to visible labels
        String label;
        switch (type.toUpperCase()) {
            case "PATIENT_COUPLE":
                label = "Patient - Couple";
                break;
            case "PATIENT_SINGLE":
                label = "Patient - Single";
                break;
            case "SPERM_DONOR":
                label = "Sperm Donor";
                break;
            case "OOCYTE_DONOR":
                label = "Egg Donor";
                break;
            case "SURROGATE":
                label = "Surrogate";
                break;
            default:
                label = type;
        }
        WebElement radioLabel = Driver.get().findElement(
                By.xpath("//label[normalize-space()='%s']|//span[normalize-space()='%s']".formatted(label, label)));
        BrowserUtils.click(radioLabel);
    }

    public void fillPatientInformation(JsonNode patient) {
        // Dropdowns by visible text
        String prefix = patient.path("prefix").asText();
        if (!prefix.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationPrefix, prefix);

        BrowserUtils.clearAndSendKeys(newRegistrationFirstName, patient.path("firstName").asText());
        BrowserUtils.clearAndSendKeys(newRegistrationMiddleName, patient.path("middleName").asText());
        BrowserUtils.clearAndSendKeys(newRegistrationLastName, patient.path("lastName").asText());

        String sex = patient.path("sexAssignedAtBirth").asText();
        if (!sex.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationSexAssigned, sex);

        String blood = patient.path("bloodGroup").asText();
        if (!blood.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationBloodGroup, blood);

        String marital = patient.path("maritalStatus").asText();
        if (marital.isEmpty())
            marital = patient.path("maritialStatus").asText();
        if (!marital.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationMaritalStatus, marital);

        String idType = patient.path("idType").asText();
        if (!idType.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationIdType, idType);
        BrowserUtils.clearAndSendKeys(newRegistrationId, patient.path("id").asText());

        String ref = patient.path("referenceSource").asText();
        if (!ref.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationReferenceSource, ref);
        BrowserUtils.clearAndSendKeys(newRegistrationAdditionalDetails, patient.path("additionalDetails").asText());

        String occ = patient.path("occupationIndustry").asText();
        if (occ.isEmpty())
            occ = patient.path("occupation/industry").asText();
        if (!occ.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationOccupationIndustry, occ);

        String reason = patient.path("reasonOfVisit").asText();
        if (!reason.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationReasonOfVisit, reason);

        String ethnicity = patient.path("ethnicity").asText();
        if (ethnicity.isEmpty())
            ethnicity = patient.path("ethinicity").asText();
        if (!ethnicity.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationEthnicity, ethnicity);

        String lang = patient.path("preferredLanguage").asText();
        if (lang.isEmpty())
            lang = patient.path("prefferedLanguage").asText();
        if (!lang.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationPreferredLanguage, lang);

        String ccMobile = patient.path("countryCode").asText();
        if (!ccMobile.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationCountryCodeMobile, ccMobile);
        BrowserUtils.clearAndSendKeys(newRegistrationMobileNumber, patient.path("mobileNumber").asText());

        String ccOffice = patient.path("officeCountryCode").asText();
        if (ccOffice.isEmpty())
            ccOffice = patient.path("countryCodeOffice").asText();
        if (!ccOffice.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationCountryCodeOffice, ccOffice);

        BrowserUtils.clearAndSendKeys(newRegistrationOfficeNumber, patient.path("officeNumber").asText());

        String email = patient.path("email").asText();
        if (email.isEmpty())
            email = patient.path("emailId").asText();
        BrowserUtils.clearAndSendKeys(newRegistrationEmailId, email);

        BrowserUtils.clearAndSendKeys(newRegistrationAddress, patient.path("address").asText());

        // Country selection
        String country = patient.path("country").asText();
        if (!country.isEmpty())
            BrowserUtils.selectAutocompleteInput(newRegistrationCountry, country);

        BrowserUtils.waitUntilEnabled(() -> newRegistrationState, 15);
        BrowserUtils.click(newRegistrationState);
        BrowserUtils.selectFirstOptionFromOpenDropdown();

        BrowserUtils.waitUntilEnabled(() -> newRegistrationCity, 15);
        BrowserUtils.click(newRegistrationCity);
        BrowserUtils.selectFirstOptionFromOpenDropdown();

        BrowserUtils.clearAndSendKeys(newRegistrationAreaCode, patient.path("areaCode").asText());
    }

    public void saveAndNext() {
        BrowserUtils.click(newRegistrationSaveAndNextButton);
        BrowserUtils.waitForPageToLoad(10);
    }

    public void register() {
        BrowserUtils.click(registerButton);
        BrowserUtils.waitForPageToLoad(10);
    }

    public void confirmRegistrationYes() {
        WebElement yes = Driver.get()
                .findElement(By.xpath("//button[normalize-space()='Yes']|//span[normalize-space()='Yes']"));
        BrowserUtils.click(yes);
        BrowserUtils.waitForPageToLoad(10);
    }

    public void verifySuccessLabel(String expectedText) {
        boolean present = BrowserUtils.isTextPresent(expectedText, 15);
        Assert.assertTrue("Success message not found: " + expectedText, present);
    }

    public void searchRegisteredPatientFromJsonAndVerify() {
        String first = com.EMR.utilities.JsonUtils.getValue("registration", "patientInfo.firstName");
        String last = com.EMR.utilities.JsonUtils.getValue("registration", "patientInfo.lastName");
        String query = (first == null ? "" : first).trim();

        BrowserUtils.typeAndEnter(searchPatient, query);

        // Verify either full name or last name appears in list view
        boolean foundFull = BrowserUtils.isTextPresent((first + " " + last).trim(), 15);
        boolean foundLast = BrowserUtils.isTextPresent(last, 5);
        Assert.assertTrue("Registered patient not found in list", foundFull || foundLast);
    }

    // --- JSON-driven helpers (preferred) ---
    // Populate Patient information directly from testData.json using JsonUtils
    public void fillPatientInformationFromJson() {
        JsonNode patient = buildPersonNodeFromTestData("patientInformation");
        fillPatientInformation(patient);
    }

    // Populate Partner information directly from testData.json using JsonUtils
    public void fillPartnerInformationFromJson() {
        JsonNode partner = buildPersonNodeFromTestData("partnerInformation");
        fillPatientInformation(partner);
    }

    // Build a JsonNode for person info (patient/partner) from testData.json using
    // JsonUtils.getValue
    private JsonNode buildPersonNodeFromTestData(String personKey) {
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        com.fasterxml.jackson.databind.node.ObjectNode node = mapper.createObjectNode();

        // Strings
        node.put("prefix", JsonUtils.getValue("registration", personKey + ".prefix"));
        node.put("firstName", JsonUtils.getValue("registration", personKey + ".firstName"));
        node.put("middleName", JsonUtils.getValue("registration", personKey + ".middleName"));
        node.put("lastName", JsonUtils.getValue("registration", personKey + ".lastName"));
        node.put("sexAssignedAtBirth", JsonUtils.getValue("registration", personKey + ".sexAssignedAtBirth"));
        node.put("bloodGroup", JsonUtils.getValue("registration", personKey + ".bloodGroup"));
        node.put("maritalStatus", JsonUtils.getValue("registration", personKey + ".maritalStatus"));
        node.put("idType", JsonUtils.getValue("registration", personKey + ".idType"));
        node.put("id", JsonUtils.getValue("registration", personKey + ".id"));
        node.put("referenceSource", JsonUtils.getValue("registration", personKey + ".referenceSource"));
        node.put("additionalDetails", JsonUtils.getValue("registration", personKey + ".additionalDetails"));
        node.put("occupationIndustry", JsonUtils.getValue("registration", personKey + ".occupationIndustry"));
        node.put("reasonOfVisit", JsonUtils.getValue("registration", personKey + ".reasonOfVisit"));
        node.put("ethnicity", JsonUtils.getValue("registration", personKey + ".ethnicity"));
        node.put("preferredLanguage", JsonUtils.getValue("registration", personKey + ".preferredLanguage"));
        node.put("countryCode", JsonUtils.getValue("registration", personKey + ".countryCode"));
        node.put("mobileNumber", JsonUtils.getValue("registration", personKey + ".mobileNumber"));
        node.put("officeCountryCode", JsonUtils.getValue("registration", personKey + ".officeCountryCode"));
        node.put("officeNumber", JsonUtils.getValue("registration", personKey + ".officeNumber"));
        node.put("email", JsonUtils.getValue("registration", personKey + ".email"));
        node.put("address", JsonUtils.getValue("registration", personKey + ".address"));
        node.put("country", JsonUtils.getValue("registration", personKey + ".country"));
        node.put("areaCode", JsonUtils.getValue("registration", personKey + ".areaCode"));

        return node;
    }

}