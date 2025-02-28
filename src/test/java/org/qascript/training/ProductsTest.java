package org.qascript.training;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * This class contains tests for validating the functionality of product selection on the SauceDemo website.
 * It sets up a WebDriver instance for interacting with the web application and performs basic setup, execution of test cases,
 * and teardown operations.
 */
public class ProductsTest {

    /**
     * The WebDriver instance used for interacting with the web browser in test automation.
     * It is responsible for navigating to web pages, performing browser actions,
     * and controlling the browser during the execution of test cases.
     */
    private WebDriver driver;

    /**
     * Sets up the testing environment before each test method executes.
     * This method initializes the WebDriver instance, maximizes the browser window,
     * navigates to the SauceDemo login page, and performs a login action using
     * predefined credentials.
     *
     * Preconditions:
     * - ChromeDriver executable must be available.
     *
     * Postconditions:
     * - The browser is open and maximized.
     * - The user is successfully logged into the SauceDemo website with the predefined
     *   credentials ("standard_user" and "secret_sauce").
     *
     * Test dependencies:
     * - This method assumes that the `LoginPage` class is properly implemented and contains
     *   the necessary locators to interact with the username field, password field, and
     *   login button on the SauceDemo login page.
     *
     * Exceptions:
     * - Any runtime exceptions during WebDriver interaction or login process will result
     *   in test failure.
     */
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

    /**
     * Verifies the functionality of selecting a product from the products page on the SauceDemo website.
     *
     * This test performs the following steps:
     * 1. Navigates to the specified inventory page URL.
     * 2. Instantiates a `ProductsPage` object to interact with the elements on the products page.
     * 3. Selects a specified product by its name, which is "Sauce Labs Backpack" in this test.
     *
     * Preconditions:
     * - The WebDriver instance is properly initialized, and the user is already logged in.
     * - The products page contains the item "Sauce Labs Backpack" available for selection.
     *
     * Postconditions:
     * - The specified product ("Sauce Labs Backpack") is successfully selected by clicking its element.
     *
     * Test dependencies:
     * - Ensures the `ProductsPage` class and its methods are correctly implemented.
     * - Assumes the presence of the product "Sauce Labs Backpack" in the inventory.
     *
     * Exceptions:
     * - Any runtime exceptions resulting from missing web elements or navigation failures
     *   will result in test failure.
     */
    @Test
    public void selectProductTest() {
        String pageUrl = "https://www.saucedemo.com/inventory.html";
        ProductsPage productsPage = new ProductsPage(driver);
        driver.get(pageUrl);
        productsPage.selectProduct("Sauce Labs Backpack");
    }

    /**
     * Tears down the testing environment after each test method execution.
     *
     * This method ensures proper cleanup by quitting the WebDriver instance if it is not null.
     * It is typically used to close the browser window at the end of each test and release associated resources.
     *
     * Preconditions:
     * - The WebDriver instance must have been initialized before the test.
     *
     * Postconditions:
     * - The WebDriver instance is terminated and the browser is closed.
     *
     * Test dependencies:
     * - This method assumes the WebDriver instance is correctly managed throughout the test lifecycle.
     * - Any tests relying on the WebDriver instance must ensure it is properly initialized in the testing setup phase.
     */
    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
