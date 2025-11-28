package com.EMR.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.EMR.utilities.BrowserUtils;

public class HomePage extends BasePage{

    @FindBy(xpath = "//button[@title='Close']")
    public WebElement close;
    
    @FindBy(xpath = "//button[@aria-haspopup='menu']")
    public WebElement loggedInUser;

    @FindBy(xpath = "//img[@alt='dropdown']")
    private WebElement profileDropdown;

    @FindBy(xpath = "//li[text()='Logout']")
    private WebElement logout;


    public void logout() {
        BrowserUtils.click(profileDropdown);
        BrowserUtils.click(logout);

    }

}
