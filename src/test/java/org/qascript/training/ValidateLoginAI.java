package org.qascript.training;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class ValidateLoginAI {

    private WebDriver driver;
    private LoginPage loginPage;
    private static final String URL = "https://www.saucedemo.com/v1/";

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(dataProvider = "loginCredentials")
    public void validateLogin(String username, String password, boolean isValid) {
        login(username, password);

        if (isValid) {
            // Validate successful login (e.g., check for redirection or an authenticated page element)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Assert.assertTrue(
                wait.until(ExpectedConditions.urlContains("inventory.html")), 
                "Login failed for valid credentials!"
            );
        } else {
            // Validate unsuccessful login (e.g., check for error message)
            Assert.assertTrue(
                    loginPage.isErrorMessageVisible(),
                "Error message not displayed for invalid credentials!"
            );
        }
    }

    private void login(String username, String password) {
        loginPage.clearFields(); // Reset fields before entering credentials
        loginPage.inputUsername.sendKeys(username);
        loginPage.inputPassword.sendKeys(password);
        loginPage.clickLoginButton.click();
    }

    @DataProvider(name = "loginCredentials")
    public Object[][] provideLoginCredentials() {
        return new Object[][]{
            {"standard_user", "secret_sauce", true},   // Valid credentials
            {"invalid_user", "wrong_password", false} // Invalid credentials
        };
    }
}