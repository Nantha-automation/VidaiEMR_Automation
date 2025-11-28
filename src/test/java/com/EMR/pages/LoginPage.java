package com.EMR.pages;

import com.EMR.utilities.BrowserUtils;
import com.EMR.utilities.JsonUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "standard-adornment-username")
    private WebElement username;

    @FindBy(id = "standard-adornment-password")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[text()='Invalid credentials']")
    public WebElement errorMessage;

    public void validLogin() {

        username.sendKeys(JsonUtils.getValue("validLogin", "username"));
        password.sendKeys(JsonUtils.getValue("validLogin", "password"));

    }

    public void inValidLogin() {

        username.sendKeys(JsonUtils.getValue("invalidLogin", "username"));
        password.sendKeys(JsonUtils.getValue("invalidLogin", "password"));
    }

    public void loginButtonClick() {
        loginButton.click();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            BrowserUtils.waitForVisibility(errorMessage, 20);
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
