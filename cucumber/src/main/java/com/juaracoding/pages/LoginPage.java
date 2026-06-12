package com.juaracoding.pages;

import com.juaracoding.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;

    public LoginPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement username;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;

    @FindBy(xpath = "//p[contains(@class,'oxd-alert-content-text')]")
    private WebElement txtInvalid;

    @FindBy(xpath = "//h5[contains(@class,'orangehrm-login-title')]")
    private WebElement txtLogin;

    @FindBy(xpath = "//span[contains(@class,'oxd-input-group__message')]")
    private WebElement txtRequired;

    public void loginUser() {
        username.sendKeys("Admin");
        password.sendKeys("admin123");
        loginBtn.click();
    }

    public void loginUsernamePassword(String username, String password) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(this.username));
        this.username.clear();
        this.username.sendKeys(username);
        this.password.clear();
        this.password.sendKeys(password);
    }

    public void setLoginBtn() {
        com.juaracoding.utils.Utils.delay(1);
        WebElement btn = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(loginBtn));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public String getTxtInvalid() {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtInvalid)).getText();
    }

    public String getTxtLogin() {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtLogin)).getText();
    }

    public String getTxtRequired() {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtRequired)).getText();
    }
}