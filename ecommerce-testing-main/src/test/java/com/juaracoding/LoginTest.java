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

public class LoginTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        logger.info("Setting up LoginTest - initializing WebDriver");
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage(driver);
        logger.info("WebDriver and LoginPage initialized successfully");
    }

    @BeforeMethod
    public void openLogin() {
        logger.debug("Opening login page before test method");
        loginPage.open();
        logger.debug("Login page opened");
    }

    @AfterClass
    public void tearDown() {
        logger.info("Tearing down LoginTest - quitting WebDriver");
        DriverSingleton.quitDriver();
        logger.info("WebDriver quit successfully");
    }

    @Test(description = "Login success dengan akun valid")
    public void testLoginSuccess() {
        logger.info("Starting testLoginSuccess");
        InventoryPage inventoryPage = loginPage.loginAs("standard_user", "secret_sauce");
        Assert.assertTrue(inventoryPage.isPageDisplayed(), "User seharusnya diarahkan ke inventory setelah login sukses");
        logger.info("testLoginSuccess completed successfully");
    }

    @Test(description = "Login gagal: username salah")
    public void testLoginWithInvalidUsername() {
        logger.info("Starting testLoginWithInvalidUsername");
        loginPage.loginAs("invalid_user", "secret_sauce");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertTrue(loginPage.getErrorMessageText().contains("Username and password do not match any user"));
        logger.info("testLoginWithInvalidUsername completed - error message displayed as expected");
    }

    @Test(description = "Login gagal: password salah")
    public void testLoginWithInvalidPassword() {
        logger.info("Starting testLoginWithInvalidPassword");
        loginPage.loginAs("standard_user", "wrong_password");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertTrue(loginPage.getErrorMessageText().contains("Username and password do not match any user"));
        logger.info("testLoginWithInvalidPassword completed - error message displayed as expected");
    }

    @Test(description = "Login gagal: tidak isi credential")
    public void testLoginWithEmptyCredentials() {
        logger.info("Starting testLoginWithEmptyCredentials");
        loginPage.loginAs("", "");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertTrue(loginPage.getErrorMessageText().contains("Username is required"));
        logger.info("testLoginWithEmptyCredentials completed - error message displayed as expected");
    }
}
