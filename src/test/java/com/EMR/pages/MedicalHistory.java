package com.EMR.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.EMR.utilities.BrowserUtils;
import com.EMR.utilities.JsonUtils;


public class MedicalHistory {

    Registration registration = new Registration();

    @FindBy(xpath = "//span[normalize-space()='Clinical']")
    public WebElement clinicalMenu;

    @FindBy(xpath = "//input[@type='radio']")
    public List<WebElement> searchByRadios;

    @FindBy(xpath = "//span[normalize-space()='Medical History']")
    public WebElement medicalHistoryMenu;


    public void navigateToMedicalHistory() {
        clinicalMenu.click();
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.selectRadioByValue(searchByRadios,JsonUtils.getNestedNode("medicalHistory", "searchByRadio").asText());
        //registration.searchRegisteredPatientFromJsonAndVerify();
        registration.clickPatientNameInTable();
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.clickSidebarSubMenu(JsonUtils.getNestedNode("medicalHistory", "sideBarMenu").asText());
        BrowserUtils.waitForPageToLoad(10);
        verifyMedicalHistoryPage();
    }

    public void verifyMedicalHistoryPage() {
        BrowserUtils.verifyURLContains(JsonUtils.getNestedNode("medicalHistory", "medicalHistoryPageUrl").asText());
        BrowserUtils.waitForVisibility(medicalHistoryMenu, 10);
    }

    public void selectConsanguinity(){
        BrowserUtils.selectRadioByValue(searchByRadios,JsonUtils.getNestedNode("medicalHistory", "consanguinity").asText());
    }

    public void fillMenstrualHistory(){

    }
    
}
