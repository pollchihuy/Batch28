package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.InventoryPage;
import com.juaracoding.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddToCartTest {

    private static final Logger logger = LoggerFactory.getLogger(AddToCartTest.class);
    private WebDriver driver;
    private InventoryPage inventoryPage;

    @BeforeClass
    public void setUp() {
        logger.info("Setting up AddToCartTest - initializing WebDriver");
        driver = DriverSingleton.getDriver();
        logger.info("WebDriver initialized successfully");
    }

    @BeforeMethod
    public void loginAndGoToInventory() {
        logger.debug("Logging in and navigating to inventory page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        inventoryPage = loginPage.loginAs("standard_user", "secret_sauce");
        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Login harus sukses sebelum melakukan pembelian.");
        logger.debug("Successfully logged in and on inventory page");
    }

    @AfterClass
    public void tearDown() {
        logger.info("Tearing down AddToCartTest - quitting WebDriver");
        DriverSingleton.quitDriver();
        logger.info("WebDriver quit successfully");
    }

    @Test(description = "Tambah 1 item ke cart")
    public void testAddItemToCart() {
        logger.info("Starting testAddItemToCart");
        inventoryPage.addFirstItemToCart();
        Assert.assertTrue(inventoryPage.isCartBadgePresent(), "Badge cart tidak muncul setelah add to cart.");
        Assert.assertEquals(inventoryPage.getCartBadgeText(), "1", "Jumlah item di cart harus 1 setelah ditambah satu.");
        logger.info("testAddItemToCart completed - item added to cart successfully");
    }

    @Test(description = "Skenario negatif: akses inventory tanpa login seharusnya redirect ke login")
    public void testAccessInventoryWithoutLogin() {
        logger.info("Starting testAccessInventoryWithoutLogin");
        DriverSingleton.quitDriver();
        driver = DriverSingleton.getDriver();
        driver.get("https://www.saucedemo.com/inventory.html");

        Assert.assertTrue(driver.getCurrentUrl().contains("www.saucedemo.com/"), "Jika tidak login, harus dialihkan ke halaman login.");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html") == false, "Tidak boleh tetap di halaman inventory jika belum login.");
        logger.info("testAccessInventoryWithoutLogin completed - redirect to login page verified");
    }
}
