package com.EMR.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.EMR.utilities.BrowserUtils;

public class Registration extends BasePage{

    @FindBy(xpath = "//li[text()='Registration']")
    private WebElement registrationMenu;

    @FindBy(xpath = "//span[text()='New Registration']")
    public WebElement newRegistrationButton;

    @FindBy(xpath = "//input[@type='radio']")
    public List<WebElement> searchByRadios;

    @FindBy(id = "searchInput")
    public WebElement searchPatient;

    @FindBy(id = "start-date")
    public WebElement startDate;

    @FindBy(id = "end-date")
    public WebElement endDate;



    public void clickRegistrationMenu() {
        BrowserUtils.click(registrationMenu);
    }

    public void selectSearchByOption(String value) {
    BrowserUtils.selectRadioByValue(searchByRadios, value);
}
    
}