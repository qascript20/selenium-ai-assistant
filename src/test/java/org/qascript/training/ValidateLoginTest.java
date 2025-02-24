package org.qascript.training;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;

public class ValidateLoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/v1/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

    @Test
    public void validateLogin() {
        loginPage.inputUsername.sendKeys("standard_user");
        loginPage.inputPassword.sendKeys("secret_sauce");
        loginPage.clickLoginButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void validateInvalidLogin() {
        loginPage.inputUsername.sendKeys("chennellbjbq");
        loginPage.inputPassword.sendKeys("wmzsWOAT17ILw1zbv");
        loginPage.clickLoginButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
