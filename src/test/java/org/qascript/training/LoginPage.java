package org.qascript.training;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.saucedemo.com/v1/
public class LoginPage {
    @FindBy(xpath = "//*[@id='user-name']")
    public WebElement inputUsername;

    @FindBy(xpath = "//*[@id='password']")
    public WebElement inputPassword;

    @FindBy(xpath = "//*[@id='login-button']")
    public WebElement clickLoginButton;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean isErrorMessageVisible() {
        return false;
    }

    public void clearFields() {
        inputUsername.clear();
        inputPassword.clear();
    }
}