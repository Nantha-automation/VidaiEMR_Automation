package com.EMR.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.EMR.utilities.BrowserUtils;
import com.EMR.utilities.JsonUtils;

public class TreatmentPlan {

    MedicalHistory medicalHistory = new MedicalHistory();
    Registration registration = new Registration();

    private String donorName;

    @FindBy(xpath = "//span[normalize-space()='Clinical']")
    public WebElement clinicalMenu;

    @FindBy(xpath = "//input[@type='radio']")
    public List<WebElement> searchByRadios;

    @FindBy(id = "searchInput")
    public WebElement searchPatient;

    @FindBy(xpath = "(//li[normalize-space(.)='Treatment Plan'])[2]")
    public WebElement treatmentPlanMenu;

    @FindBy(xpath = "//button[normalize-space()='Procedure']")
    public WebElement procedureTab;

    @FindBy(xpath = "//button[normalize-space()='Procedure' and @aria-selected='true']")
    public WebElement procedureTabSelected;

    @FindBy(xpath = "//button[normalize-space()='Drugs']")
    public WebElement drugsTab;

    @FindBy(xpath = "//input[@placeholder='Search by Procedure, Services, code']")
    public WebElement searchInput;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    public WebElement addButton;

    @FindBy(xpath = "//div[@role='dialog']//input[@placeholder='Search Donor']")
    public WebElement searchDonorInput;

    @FindBy(xpath = "//button[normalize-space()='Link Donor']")
    public WebElement linkDonorButton;

    @FindBy(xpath = "//button[normalize-space()='Yes']")
    public WebElement yesButton;

    @FindBy(xpath = "//button[normalize-space()='No']")
    public WebElement noButton;

    @FindBy(xpath = "//input[@placeholder='Select Indication']")
    public WebElement selectIndicationInput;

    @FindBy(xpath = "//label[normalize-space()='Notes']/following::textarea[@placeholder='Type here']")
    public WebElement notesTextArea;

    @FindBy(xpath = "//button[normalize-space()='Add Procedure']")
    public WebElement addProcedureButton;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    public WebElement cancelButton;

    @FindBy(xpath = "//p[normalize-space()='View All']")
    public WebElement viewAll;

    @FindBy(xpath = "//button[not(@disabled) and .//img[contains(@alt,'Add Icon')]]")
    public List<WebElement> addIcon;

    @FindBy(xpath = "//button[.//img[contains(@alt,'Added Icon')]]")
    public List<WebElement> addedOptionsToCart;

    @FindBy(xpath = "//img[@alt='Cart Icon']/ancestor::button/preceding-sibling::span")
    public WebElement countOfAddedOptionsToCart;

    @FindBy(xpath = "//img[@alt='Cart Icon']/ancestor::button")
    public WebElement cartButton;

    @FindBy(xpath = "//label[normalize-space()='Select Doctor']/following::input[@role='combobox']")
    public WebElement selectDoctorInput;

    @FindBy(xpath = "//ul[@role='listbox']/li[1]")
    public WebElement firstOptionInDropdown;

    @FindBy(xpath = "//button[normalize-space()='Proceed']")
    public WebElement proceedButtonInCart;

    @FindBy(xpath = "//input[@placeholder='Search by Drug Name, Generic Name']")
    public WebElement searchByDrugNameInput;

    @FindBy(xpath = "//label[contains(normalize-space(), 'Day(s)')]/following::input[@type='number']")
    public WebElement drugsDaysInput;

    @FindBy(xpath = "//label[normalize-space()='Notes']/following::input[@type='text']")
    public WebElement drugsNotesInput;

    @FindBy(xpath = "//button[normalize-space()='Place Order']")
    public WebElement drugsPlaceOrderButton;

    @FindBy(xpath = "//button[.//img[@alt='Back']]")
    public WebElement drugsBackButton;

    @FindBy(xpath = "//button[contains(@class,'MuiIconButton-colorError')]//*[name()='svg']")
    public WebElement deleteIcon;

    public void navigateToTreatmentPlan() {
        clinicalMenu.click();
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.selectRadioByValue(searchByRadios,
                JsonUtils.getNestedNode("treatmentPlan", "searchByRadio").asText());
        BrowserUtils.searchByFullNameFromJsonAndVerifyInPatientTable(searchPatient, 5, "searchPatient",
                "treatmentPlan");
        BrowserUtils.clickPatientNameInTable(JsonUtils.getNestedNode("treatmentPlan", "searchPatient").asText());
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.click(treatmentPlanMenu);
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.verifyURLContains(JsonUtils.getNestedNode("treatmentPlan", "treatmentPlanPageUrl").asText());
    }

    public void mouseHoverOnProfile() {
        String sex = BrowserUtils.hoverProfileAndGetSexAssignedAtBirth(1);

        if (sex.equalsIgnoreCase("Female")) {
            performFemaleActions();
        } else if (sex.equalsIgnoreCase("Male")) {
            performMaleActions();
        } else {
            System.out.println("⚠ Unknown Sex Assigned at Birth: " + sex);
        }
    }

    public void verifyOrClickProcedureTab() {
        try {
            // Check if selected
            BrowserUtils.waitForVisibility(procedureTabSelected, 2);
            System.out.println("✔ 'Procedure' tab is already selected");
        } catch (Exception e) {
            // Not selected, so click it
            System.out.println("ℹ 'Procedure' tab not selected. Selecting now...");
            BrowserUtils.click(procedureTab);
            BrowserUtils.waitForPageToLoad(10);
        }
    }

    public void clickAddButton() {
        BrowserUtils.click(addButton);
        BrowserUtils.waitForPageToLoad(10);
    }

    public void clickCycleRelatedProcedure(String moduleName) {
        By locator = By.xpath(String.format(
                "//div[contains(@class,'MuiPaper-root')]//*[normalize-space()='%s']/ancestor::div[contains(@class,'MuiPaper-root')][1]",
                moduleName));

        WebElement cycleRelatedProcedure = BrowserUtils.waitForClickablility(locator, 10);
        cycleRelatedProcedure.click();
    }

    public void clickSourceCheckBox(String groupName, String option) {

        By locator;

        if (option.equalsIgnoreCase("Donor")) {
            locator = By.xpath(String.format(
                    "//span[normalize-space()='%s']/ancestor::div[contains(@class,'MuiGrid2-root')][1]//p[normalize-space()='%s']",
                    groupName, option));
            selectDonorFromDialog(donorName);
        } else {
            locator = By.xpath(String.format(
                    "//span[normalize-space()='%s']/following::label[.//*[normalize-space()='%s']][1]",
                    groupName, option));
        }

        WebElement element = BrowserUtils.waitForClickablility(locator, 10);
        element.click();
    }

    public void selectDonorFromDialog(String donorName) {
        // search field inside donor dialog
        BrowserUtils.clearAndSendKeys(searchDonorInput, donorName);

        // wait for results and click matching donor row
        By donorRow = By.xpath("//tr[.//*[normalize-space()='" + donorName + "']]//input[@type='checkbox']");

        WebElement row = BrowserUtils.waitForClickablility(donorRow, 10);
        row.click();

        // click linkDonor button
        BrowserUtils.click(linkDonorButton);
        BrowserUtils.click(yesButton);
        BrowserUtils.waitForPageToLoad(10);
    }

    public void selectIndication(String indication) {
        BrowserUtils.clearAndSendKeys(selectIndicationInput, indication);
        // select first option from dropdown
        BrowserUtils.waitForClickablility(firstOptionInDropdown, 10);
        firstOptionInDropdown.click();
    }

    public void enterNotes(String notes) {
        BrowserUtils.clearAndSendKeys(notesTextArea, notes);
    }

    public void addProcedureClick(String notes) {
        BrowserUtils.click(addProcedureButton);
        BrowserUtils.waitForPageToLoad(10);
        
    }

    public List<String> selectNRandomUnselectedOptions(int count) {
        List<String> selectedNames = new ArrayList<>();

        // 0️⃣ Click View All only if displayed — do NOT fail if not present
        try {
            if (viewAll.isDisplayed()) {
                BrowserUtils.waitForClickablility(viewAll, 5).click();
                BrowserUtils.waitFor(1); // wait for UI to expand
                System.out.println("Clicked on 'View All'");
            }
        } catch (Exception e) {
            // Element not present or not visible → ignore and continue
            System.out.println("'View All' not found. Proceeding without clicking.");
        }

        // 1️⃣ Now continue selecting unselected options
        if (addIcon == null || addIcon.isEmpty()) {
            System.out.println("No unselected options available");
            return selectedNames;
        }

        // Shuffle list randomly
        Collections.shuffle(addIcon);

        int limit = Math.min(count, addIcon.size());

        for (int i = 0; i < limit; i++) {
            WebElement addBtn = addIcon.get(i);

            try {
                // find the aria-label text for the item
                WebElement pTag = addBtn.findElement(
                        By.xpath("./ancestor::div[contains(@class,'MuiPaper-root')][1]//p[@aria-label]"));
                String ariaText = pTag.getAttribute("aria-label").trim();
                selectedNames.add(ariaText);

                // click + icon
                BrowserUtils.waitForClickablility(addBtn, 10).click();
                BrowserUtils.waitFor(1);

                System.out.println("Selected: " + ariaText);
            } catch (Exception e) {
                System.out.println("Failed to select option: " + e.getMessage());
            }
        }

        return selectedNames;
    }


    public int getSelectedCountFromGrid() {
        // All selected items = buttons with the Added Icon (tick)
        int count = addedOptionsToCart.size();
        System.out.println("Selected items in grid (based on Added Icon): " + count);
        return count;
    }

    public void verifyCartCountFromGridAndOpenCart() {
        // 1️⃣ Get expected count from grid
        int expectedCount = getSelectedCountFromGrid();

        // 2️⃣ Read badge from cart
        WebElement count = BrowserUtils.waitForVisibility(countOfAddedOptionsToCart, 10);
        String countText = count.getText().trim();
        int actualCount = Integer.parseInt(countText);

        // 3️⃣ Assert both counts match
        Assert.assertEquals("Cart count does not match selected items in grid",
                expectedCount, actualCount);
        System.out.println("✔ Cart badge count verified. Expected = " + expectedCount +
                ", Actual = " + actualCount);

        // 4️⃣ Click on cart icon
        BrowserUtils.waitForClickablility(cartButton, 10);
        cartButton.click();
    }

    public void selectDoctor(String doctorName) {
        // Click the dropdown input
        BrowserUtils.waitForClickablility(selectDoctorInput, 10);
        selectDoctorInput.click();

        // Type the name
        selectDoctorInput.sendKeys(doctorName);
        BrowserUtils.waitFor(1);

        // Select first suggestion
        BrowserUtils.waitForClickablility(firstOptionInDropdown, 10);
        firstOptionInDropdown.click();
    }

    public void clickProceedInCart() {
        BrowserUtils.click(proceedButtonInCart);
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.click(yesButton);

    }

    public String getCellValueByColumn(String columnName) {

        String xpath = "//table//tbody//tr/td[count(//table//th[normalize-space()='"
                + columnName + "']/preceding-sibling::th)+1]//*[normalize-space()]";

        By cellLocator = By.xpath(xpath);

        WebElement cell = BrowserUtils.waitForVisibility(cellLocator, 10);

        return cell.getText().trim();
    }

    public boolean isCellValueMatching(String columnName, String expectedText) {

        String xpath = "//table//tbody//tr/td[count(//table//th[normalize-space()='"
                + columnName + "']/preceding-sibling::th)+1]//*[normalize-space()]";

        By cellLocator = By.xpath(xpath);

        WebElement cell = BrowserUtils.waitForVisibility(cellLocator, 10);
        String actualText = cell.getText().trim();

        return actualText.equals(expectedText) || actualText.contains(expectedText);
    }


    public void clickDrugsTab() {
        BrowserUtils.click(drugsTab);
        BrowserUtils.waitForPageToLoad(10);
    }

    public void searchDrugByName(String drugName) {
        BrowserUtils.clearAndSendKeys(searchByDrugNameInput, drugName);
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.waitForClickablility(firstOptionInDropdown, 10);
        firstOptionInDropdown.click();
    }

    public void fillDropdownsInDrugsTab(String labelText, String value) {
        // Find input based on label text without index
        By fieldBy = By.xpath("//input[@id=//label[contains(normalize-space(),'" + labelText + "')]/@id]");
        WebElement field = BrowserUtils.waitForClickablility(fieldBy, 10);
        field.click();
        field.sendKeys(value);
        BrowserUtils.waitFor(1);
        BrowserUtils.waitForClickablility(firstOptionInDropdown, 10).click();
    }

    public void fillDrugsDaysAndNotes(String days, String notes) {
        BrowserUtils.clearAndSendKeys(drugsDaysInput, days);
        BrowserUtils.clearAndSendKeys(drugsNotesInput, notes);
    }

    public void clickPlaceOrderInDrugsTab() {
        BrowserUtils.click(drugsPlaceOrderButton);
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.click(yesButton);
        BrowserUtils.waitForPageToLoad(10);
    }

    public void clickBackInDrugsTab() {
        BrowserUtils.click(drugsBackButton);
        BrowserUtils.waitForPageToLoad(10);
    }

    public void clickDeleteIcon() {
        BrowserUtils.click(deleteIcon);
    }

    public void performFemaleActions() {
        System.out.println("Performing  female-specific actions in Treatment Plan");

    }

    public void performMaleActions() {
        System.out.println("Performing  male-specific actions in Treatment Plan");

    }
}
