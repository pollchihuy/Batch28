package com.juaracoding.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {
    private final WebDriver driver;

    @FindBy(css = ".inventory_list")
    private WebElement inventoryList;

    @FindBy(css = ".inventory_item .btn_inventory")
    private WebElement firstAddToCartButton;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartLink;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageDisplayed() {
        return driver.getCurrentUrl().contains("/inventory.html") && inventoryList.isDisplayed();
    }

    public void addFirstItemToCart() {
        firstAddToCartButton.click();
    }

    public String getCartBadgeText() {
        return cartBadge.getText().trim();
    }

    public boolean isCartBadgePresent() {
        return cartBadge.isDisplayed();
    }

    public void clickCart() {
        cartLink.click();
    }
}
