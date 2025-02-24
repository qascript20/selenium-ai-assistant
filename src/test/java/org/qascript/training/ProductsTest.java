package org.qascript.training;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductsTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Perform login
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUsername.sendKeys("standard_user");
        loginPage.inputPassword.sendKeys("secret_sauce");
        loginPage.clickLoginButton.click();
    }

    @Test
    public void selectProductTest() {
        String pageUrl = "https://www.saucedemo.com/inventory.html";
        ProductsPage productsPage = new ProductsPage(driver);
        driver.get(pageUrl);
        productsPage.selectProduct("Sauce Labs Backpack");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
