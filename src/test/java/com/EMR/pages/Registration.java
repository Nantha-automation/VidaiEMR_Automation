package com.EMR.pages;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.cucumber.java.Scenario;
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

    @FindBy(xpath = "//*[@id='start-date' or @id='date-of-birth']")
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

    @FindBy(xpath = "//label[contains(normalize-space(),'First Name')]/ancestor::div[contains(@class,'MuiFormControl-root')]//input[not(@disabled)]")
    public WebElement newRegistrationFirstName;

    @FindBy(xpath = "//label[contains(normalize-space(),'Middle Name')]/ancestor::div[contains(@class,'MuiFormControl-root')]//input[not(@disabled)]")
    public WebElement newRegistrationMiddleName;

    @FindBy(xpath = "//label[contains(normalize-space(),'Last Name')]/ancestor::div[contains(@class,'MuiFormControl-root')]//input[not(@disabled)]")
    public WebElement newRegistrationLastName;

    @FindBy(id = "sex-assigned-select")
    public WebElement newRegistrationSexAssigned;

    @FindBy(id = "demo-simple-select")
    public WebElement newRegistrationBloodGroup;

    @FindBy(xpath = "//div[label[normalize-space()='Marital Status']]//div[@role='combobox']")
    public WebElement newRegistrationMaritalStatus;

    @FindBy(xpath = "//label[contains(normalize-space(),'ID Type')]/following-sibling::div//div[@role='combobox']")
    public WebElement newRegistrationIdType;  

    @FindBy(xpath = "//label[contains(normalize-space(),'ID #')]/ancestor::div[contains(@class,'MuiFormControl-root')]//input[not(@disabled)]")
    public WebElement newRegistrationId;

    @FindBy(xpath = "//div[label[normalize-space()='Reference Source']]//div[@role='combobox']")
    public WebElement newRegistrationReferenceSource;

    @FindBy(xpath = "//label[contains(normalize-space(),'Additional Details')]/ancestor::div[contains(@class,'MuiFormControl-root')]//input[not(@disabled)]")
    public WebElement newRegistrationAdditionalDetails;

    @FindBy(id = "occupation-industry-autocomplete")
    public WebElement newRegistrationOccupationIndustry;

    @FindBy(xpath = "//label[contains(normalize-space(),'Reason of Visit')]/following-sibling::div//input[@type='text']")
    public WebElement newRegistrationReasonOfVisit;

    @FindBy(xpath = "//*[@id='ethnicity-select' or @id='outlined-select-ethnicity']")
    public WebElement newRegistrationEthnicity;

    @FindBy(id = "preferred-language-select")
    public WebElement newRegistrationPreferredLanguage;

    @FindBy(id = "body-type-select")
    public WebElement newRegistrationbodyType;

    @FindBy(id = "eye-color-select")
    public WebElement newRegistrationEyeColor;

    @FindBy(id = "handedness-select")
    public WebElement newRegistrationHandedness;

    @FindBy(xpath = "//label[contains(normalize-space(),'Height')]/following::input[1]")
    public WebElement newRegistrationHeight;

    @FindBy(xpath = "//label[contains(normalize-space(),'Weight')]/following::input[1]")
    public WebElement newRegistrationWeight;

    @FindBy(xpath = "//label[contains(normalize-space(),'BMI')]/following::input[1]")
    public WebElement newRegistrationBMI;

    @FindBy(id = "child-hair-color")
    public WebElement newRegistrationChildHairColor;

    @FindBy(id = "adult-hair-color")
    public WebElement newRegistrationAdultHairColor;

    @FindBy(id = "shade")
    public WebElement newRegistrationShade;

    @FindBy(id = "shade-type")
    public WebElement newRegistrationShadeType;

    @FindBy(id = "fullness")
    public WebElement newRegistrationFullness;

    @FindBy(id = "texture")
    public WebElement newRegistrationTexture;

    @FindBy(id = "Tone")
    public WebElement newRegistrationTone;

    @FindBy(id = "condition")
    public WebElement newRegistrationCondition;

    @FindBy(xpath = "//*[@id='acne' and @role='combobox' and ancestor::div[label[normalize-space()='Acne']]]")
    public WebElement newRegistrationAcne;

    @FindBy(id = "age-acne-occurred")
    public WebElement newRegistrationAgeAcneOccurred;

    @FindBy(xpath = "//div[label[normalize-space()='Treatment Required']]//input[@type='checkbox']")
    public WebElement newRegistrationTreatmentRequired;

    @FindBy(id = "skin-condition")
    public WebElement newRegistrationMole;

    @FindBy(xpath = "//*[@id='acne' and @role='combobox' and ancestor::div[label[normalize-space()='Freckles']]]")
    public WebElement newRegistrationFreckles;

    @FindBy(id = "acne-severity")
    public WebElement newRegistrationDimples;

    @FindBy(id = "vision-select")
    public WebElement newRegistrationVision;

    @FindBy(xpath = "//label[normalize-space()='Correction']/following-sibling::div//div[@role='combobox']")
    public WebElement newRegistrationCorrection;

    @FindBy(xpath = "//div[label[normalize-space()='Age when prescribed']]//input[@type='text']")
    public WebElement newRegistrationAgeWhenPrescribed;

    @FindBy(xpath = "//div[label[normalize-space()='Any Hearing Difficulties']]//input[@type='checkbox']")
    public WebElement newRegistrationHearingDifficulties;

    @FindBy(xpath = "//div[label[normalize-space()='Describe difficulty']]//input[@type='text']")
    public WebElement newRegistrationDescribeDifficulty;

    @FindBy(xpath = "//label[normalize-space()='Devices']/following-sibling::div//div[@role='combobox']")
    public WebElement newRegistrationDevices;

    @FindBy(xpath = "//label[contains(normalize-space(),'Reason for Devices')]/following-sibling::div//div[@role='combobox']")
    public WebElement newRegistrationReasonForDevices;

    @FindBy(xpath = "//div[label[normalize-space()='Ages Used']]//input[@type='text']")
    public WebElement newRegistrationAgesUsed;

    @FindBy(xpath = "(//label[contains(normalize-space(),'Country Code')]/following-sibling::div//input[@type='text'])[1]")
    public WebElement newRegistrationCountryCodeMobile;

    @FindBy(id = "mobile-number")
    public WebElement newRegistrationMobileNumber;

    @FindBy(xpath = "(//label[contains(normalize-space(),'Country Code')]/following-sibling::div//input[@type='text'])[2]")
    public WebElement newRegistrationCountryCodeOffice;

    @FindBy(xpath = "//*[@id='office-number' or @id='alternate-mobile']")
    public WebElement newRegistrationOfficeNumber;

    @FindBy(id = "email-id")
    public WebElement newRegistrationEmailId;

    @FindBy(xpath = "(//input[@id='address']) | (//label[contains(normalize-space(),'Address Line 1') or contains(normalize-space(),'Address')]/following-sibling::div//input[@type='text'])")
    public WebElement newRegistrationAddress;

    @FindBy(xpath = "(//label[contains(., 'Country')]/following::input[@role='combobox'])[3]")
    public WebElement newRegistrationCountry;

    @FindBy(xpath = "//label[contains(., 'State')]/following::input[@role='combobox'][1]")
    public WebElement newRegistrationState;

    @FindBy(xpath = "//label[contains(., 'City')]/following::input[@role='combobox'][1]")
    public WebElement newRegistrationCity;

    @FindBy(xpath = "//label[contains(normalize-space(),'Area Code')]/following-sibling::div//input[@type='text']")
    public WebElement newRegistrationAreaCode;

    @FindBy(xpath = "//div[label[normalize-space()='Ever Applied/Screened to be Sperm Donor']]//input[@type='checkbox']")
    public WebElement newRegistrationEverAppliedScreenedToBeSpermDonor;

    @FindBy(xpath = "//div[label[normalize-space()='Donated Sperm']]//input[@type='checkbox']")
    public WebElement newRegistrationDonatedSperm;

    @FindBy(xpath = "//div[label[normalize-space()='Donor Program Name']]//input[@type='text']")
    public WebElement newRegistrationDonorProgramName;

    @FindBy(xpath = "//div[label[normalize-space()='Donation Count']]//input[@type='text']")
    public WebElement newRegistrationDonationCount;

    @FindBy(xpath = "//div[label[normalize-space()='Donation Cycle']]//input[@type='text']")
    public WebElement newRegistrationDonationCycle;

    @FindBy(xpath = "//div[label[normalize-space()='Ever Applied/Screened to be an Egg Donor']]//input[@type='checkbox']")
    public WebElement newRegistrationEverAppliedScreenedToBeEggDonor;

    @FindBy(xpath = "//div[label[normalize-space()='Donated Egg']]//input[@type='checkbox']")
    public WebElement newRegistrationDonatedEgg;

    @FindBy(xpath = "//div[label[normalize-space()='Consulted with your family when completing family medical history']]//input[@type='checkbox']")
    public WebElement newRegistrationConsultedWithYourFamilyWhenCompletingFamilyMedicalHistory;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    public WebElement newRegistrationCancelButton;

    @FindBy(xpath = "//button[normalize-space()='Save & Next']")
    public WebElement newRegistrationSaveAndNextButton;

    @FindBy(xpath = "//button[normalize-space()='Register']")
    public WebElement registerButton;

    @FindBy(xpath = "//label[normalize-space()='Exit Patient']/ancestor::div[contains(@class,'sidebarComponent_userCard')]")
    public WebElement exitPatient;

    public void clickRegistrationMenu() {
        BrowserUtils.click(registrationMenu);
    }

    public void clickExitPatient() {
        BrowserUtils.click(exitPatient);
        BrowserUtils.waitForPageToLoad(15);
    }

    public void selectSearchByOption(String value) {
        BrowserUtils.selectRadioByValue(searchByRadios, value);
    }

    
    /**
     * Selects the appropriate SearchBy radio button based on the scenario tag.
     * Extracts the type tag from scenario (e.g., @patient, @sperm_donor) and matches it 
     * with the corresponding value in the searchBy array from JSON test data.
     * 
     * @param scenario The Cucumber scenario containing tags
     * @throws AssertionError if no matching searchBy value found for the tag
     */
    public void selectSearchByRadioOptionUsingTag(Scenario scenario) {
        // 1. Find the "type tag" from scenario (e.g. @patient, @sperm_donor)
        List<String> genericTags = Arrays.asList("@register", "@registration", "@regression", "@smoke");
        String typeTag = scenario.getSourceTagNames().stream()
                .map(String::toLowerCase)
                .filter(t -> !genericTags.contains(t)) // ignore generic tags
                .findFirst()
                .orElse("")
                .replace("@", "") // "@sperm_donor" -> "sperm_donor"
                .toUpperCase(); // "sperm_donor" -> "SPERM_DONOR"

        // Handle empty tag case
        if (typeTag.isEmpty()) {
            throw new AssertionError("No type-specific tag found in scenario. Expected tags like @patient, @sperm_donor, @oocyte_donor, or @surrogate");
        }

        // 2. Match with JSON array: "searchBy": ["patient","SPERM_DONOR","OOCYTE_DONOR","SURROGATE"]
        List<String> searchByArray = JsonUtils.getArrayValues("registration", "searchBy");
        String matchedValue = "";
        for (String value : searchByArray) {
            if (value.equalsIgnoreCase(typeTag)) {
                matchedValue = value;
                break;
            }
        }

        if (matchedValue.isEmpty()) {
            throw new AssertionError("No matching searchBy value found in JSON for tag: @" + typeTag.toLowerCase() + 
                    ". Available values in JSON: " + searchByArray);
        }

        // 3. Reuse your existing method
        selectSearchByOption(matchedValue);
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
        BrowserUtils.waitForPageToLoad(10);
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
        // Use XPath to click with retry mechanism to handle stale elements
        String xpath = "//label[normalize-space()='%s']|//span[normalize-space()='%s']".formatted(label, label);
        BrowserUtils.clickWithWait(By.xpath(xpath), 3);
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

        String dob = patient.path("dob").asText();
        if (!dob.isEmpty())
            BrowserUtils.clearAndSendKeys(startDate, dob);

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
            BrowserUtils.selectAutocompleteInput(newRegistrationCountryCodeMobile, ccMobile);
            BrowserUtils.selectFirstOptionFromOpenDropdown();
        BrowserUtils.clearAndSendKeys(newRegistrationMobileNumber, patient.path("mobileNumber").asText());

        String ccOffice = patient.path("officeCountryCode").asText();
        if (ccOffice.isEmpty())
            ccOffice = patient.path("countryCodeOffice").asText();
        if (!ccOffice.isEmpty())
            BrowserUtils.selectAutocompleteInput(newRegistrationCountryCodeOffice, ccOffice);
            BrowserUtils.selectFirstOptionFromOpenDropdown();

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
            BrowserUtils.selectFirstOptionFromOpenDropdown();
        BrowserUtils.waitUntilEnabled(() -> newRegistrationState, 15);
        BrowserUtils.click(newRegistrationState);
        BrowserUtils.selectFirstOptionFromOpenDropdown();

        BrowserUtils.waitUntilEnabled(() -> newRegistrationCity, 15);
        BrowserUtils.click(newRegistrationCity);
        BrowserUtils.selectFirstOptionFromOpenDropdown();

        BrowserUtils.clearAndSendKeys(newRegistrationAreaCode, patient.path("areaCode").asText());
    }

    public void commonFieldsforSpermAndEggDonor(JsonNode patient) {
        String bodyType = patient.path("bodyType").asText();
        if (bodyType.isEmpty())
            bodyType = patient.path("bodyType").asText();
        if (!bodyType.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationbodyType, bodyType);

        String eyeColor = patient.path("eyeColor").asText();
        if (eyeColor.isEmpty())
            eyeColor = patient.path("eyeColor").asText();
        if (!eyeColor.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationEyeColor, eyeColor);

        String handedness = patient.path("handedness").asText();
        if (handedness.isEmpty())
            handedness = patient.path("handedness").asText();
        if (!handedness.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationHandedness, handedness);

        BrowserUtils.clearAndSendKeys(newRegistrationHeight, patient.path("height").asText());
        BrowserUtils.clearAndSendKeys(newRegistrationWeight, patient.path("weight").asText());
        
        String hairColorChild = patient.path("childHairColor").asText();
        if (!hairColorChild.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationChildHairColor, hairColorChild);
        
        String hairColorAdult = patient.path("adultHairColor").asText();
        if (!hairColorAdult.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationAdultHairColor, hairColorAdult);

        String shade = patient.path("shade").asText();
        if (!shade.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationShade, shade);

        String shadeType = patient.path("shadeType").asText();
        if (!shadeType.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationShadeType, shadeType);

        String fullness = patient.path("fullness").asText();
        if (!fullness.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationFullness, fullness);

        String texture = patient.path("texture").asText();
        if (!texture.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationTexture, texture);

        String tone = patient.path("tone").asText();
        if (!tone.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationTone, tone);

        String condition = patient.path("condition").asText();
        if (!condition.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationCondition, condition);

        String acne = patient.path("acne").asText();
        if (!acne.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationAcne, acne);

        BrowserUtils.waitUntilEnabled(() -> newRegistrationAgeAcneOccurred, 15);
        BrowserUtils.clearAndSendKeys(newRegistrationAgeAcneOccurred, patient.path("ageWhenAcneOccurred").asText());

        BrowserUtils.selectCheckBox(newRegistrationTreatmentRequired,patient.path("treatmentRequired").asBoolean());

        String mole = patient.path("mole").asText();
        if (!mole.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationMole, mole);

        String freckles = patient.path("freckles").asText();
        if (!freckles.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationFreckles, freckles); 

        String dimples = patient.path("dimples").asText();
        if (!dimples.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationDimples, dimples);

        String vision = patient.path("vision").asText();
        if (!vision.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationVision, vision);

        String correction = patient.path("correction").asText();
        if (!correction.isEmpty())
            BrowserUtils.waitUntilEnabled(() -> newRegistrationAgeAcneOccurred, 15);
            BrowserUtils.selectFromDropdown(newRegistrationCorrection, correction);

        BrowserUtils.waitUntilEnabled(() -> newRegistrationAgeWhenPrescribed, 15);
        BrowserUtils.clearAndSendKeys(newRegistrationAgeWhenPrescribed, patient.path("ageWhenPrescribed").asText());

        BrowserUtils.selectCheckBox(newRegistrationHearingDifficulties, patient.path("anyHearingDifficulties").asBoolean());

        BrowserUtils.waitUntilEnabled(() -> newRegistrationDescribeDifficulty, 15);
        BrowserUtils.clearAndSendKeys(newRegistrationDescribeDifficulty, patient.path("describeDifficulty").asText());

        String devices = patient.path("devices").asText();
        if (!devices.isEmpty())
            BrowserUtils.selectFromDropdown(newRegistrationDevices, devices);

        String reasonForDevices = patient.path("reasonForDevices").asText();
        if (!reasonForDevices.isEmpty())
            BrowserUtils.waitUntilEnabled(() -> newRegistrationReasonForDevices, 15);
            BrowserUtils.selectFromDropdown(newRegistrationReasonForDevices, reasonForDevices);

        BrowserUtils.clearAndSendKeys(newRegistrationAgesUsed, patient.path("agesUsed").asText());

        BrowserUtils.clearAndSendKeys(newRegistrationDonorProgramName, patient.path("donorProgramName").asText());

        BrowserUtils.selectCheckBox(newRegistrationConsultedWithYourFamilyWhenCompletingFamilyMedicalHistory, patient.path("consultedWithYourFamily").asBoolean());
    }

    public void fillSperm_donorInformation(JsonNode patient) {
        fillPatientInformation(patient);
        commonFieldsforSpermAndEggDonor(patient);

        BrowserUtils.selectCheckBox(newRegistrationEverAppliedScreenedToBeSpermDonor, patient.path("everAppliedScreenedToBeSpermDonor").asBoolean());

        BrowserUtils.selectCheckBox(newRegistrationDonatedSperm, patient.path("donatedSperm").asBoolean());

        BrowserUtils.clearAndSendKeys(newRegistrationDonationCount, patient.path("donationCount").asText());
    }

    public void fillEgg_donorInformation(JsonNode patient) {
        fillPatientInformation(patient);
        commonFieldsforSpermAndEggDonor(patient);

        BrowserUtils.selectCheckBox(newRegistrationEverAppliedScreenedToBeEggDonor, patient.path("everAppliedScreenedToBeEggDonor").asBoolean());

        BrowserUtils.selectCheckBox(newRegistrationDonatedEgg, patient.path("donatedEgg").asBoolean());

        BrowserUtils.clearAndSendKeys(newRegistrationDonationCycle, patient.path("donationCycle").asText());
    }

    public void saveAndNext() {
        BrowserUtils.click(newRegistrationSaveAndNextButton);
        BrowserUtils.waitForPageToLoad(10);
    }

    public void register() {
        BrowserUtils.click(registerButton);
        BrowserUtils.waitForPageToLoad(15);
    }

    public void confirmRegistrationYes() {
        WebElement yes = Driver.get()
                .findElement(By.xpath("//button[normalize-space()='Yes']|//span[normalize-space()='Yes']"));
        BrowserUtils.click(yes);
        BrowserUtils.waitForPageToLoad(10);
    }

    public void verifySuccessLabel(String expectedText) {
        boolean present = BrowserUtils.isTextPresent(expectedText, 10);
        Assert.assertTrue("Success message not found: " + expectedText, present);
    }

    public void searchRegisteredPatientFromJsonAndVerify(String infoKey) {
    // infoKey = "patientInformation" / "spermDonorInformation" / "oocyteDonorInformation" / "surrogateInformation"

    JsonNode patientInfo = JsonUtils.getNestedNode("registration", "newRegistration", infoKey);
    if (patientInfo == null || patientInfo.isMissingNode()) {
        throw new IllegalStateException("No JSON node found for path registration.newRegistration." + infoKey);
    }

    String first = patientInfo.path("firstName").asText();
    String last  = patientInfo.path("lastName").asText();
    String fullName = (first + " " + last).trim();
    // Search using full name (first name + last name)
    BrowserUtils.clearAndSendKeys(searchPatient, fullName);
    BrowserUtils.waitFor(3);
    // Verify full name appears in the Patient column of the table
    boolean foundInTable = BrowserUtils.isPatientPresentInTable(fullName, 5);
    Assert.assertTrue("Registered patient '" + fullName + "' not found in patient table", foundInTable);
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

    public void fillPatientSingleInformationFromJson() {
        JsonNode patient = buildPersonNodeFromTestData("patient-singleInformation");
        fillPatientInformation(patient);
    }

    public void fillSpermDonorInformationFromJson() {
        JsonNode patient = buildPersonNodeFromTestData("sperm_donorInformation");
        fillSperm_donorInformation(patient);
    }

    public void fillEggDonorInformationFromJson() {
        JsonNode patient = buildPersonNodeFromTestData("egg_donorInformation");
        fillEgg_donorInformation(patient);
    }

    public void fillSurrogateInformationFromJson() {
        JsonNode patient = buildPersonNodeFromTestData("surrogateInformation");
        fillEgg_donorInformation(patient);
    }

    // Build a JsonNode for person info (patient/partner) from testData.json using JsonUtils
    private JsonNode buildPersonNodeFromTestData(String personKey) {
        // Get the nested node: registration.newRegistration.patientInformation (or partnerInformation)
        JsonNode personNode = JsonUtils.getNestedNode("registration", "newRegistration", personKey);
        
        if (personNode == null || personNode.isMissingNode()) {
            throw new IllegalStateException("Missing registration.newRegistration." + personKey + " in testData.json");
        }
        
        return personNode;
    }

}