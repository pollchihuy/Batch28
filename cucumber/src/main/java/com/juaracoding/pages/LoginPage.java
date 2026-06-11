package com.juaracoding.pages;

import com.juaracoding.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        this.username.clear();
        this.username.sendKeys(username);
        this.password.clear();
        this.password.sendKeys(password);
    }

    public void setLoginBtn() {
        loginBtn.click();
    }

    public String getTxtInvalid() {
        return txtInvalid.getText();
    }

    public String getTxtLogin() {
        return txtLogin.getText();
    }

    public String getTxtRequired() {
        return txtRequired.getText();
    }
}