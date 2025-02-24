package org.qascript.training;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.saucedemo.com/inventory.html
public class ProductsPage {

    // Locate a product using XPath
    @FindBy(xpath = "//div[contains(@class, 'inventory_item') and .//div[text()='Sauce Labs Backpack']]")
    private WebElement productSauceLabsBackpack;

    public void selectProduct(String productName) {
        productSauceLabsBackpack.click();
    }


    public ProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}