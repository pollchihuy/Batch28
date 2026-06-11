package com.juaracoding.pages;

import com.juaracoding.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    private WebDriver driver;

    public DashboardPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[contains(@class,'oxd-topbar-header-breadcrumb')]")
    private WebElement txtDashboard;

    @FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
    private WebElement userDropdown;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    private WebElement logoutBtn;

    public void setLogoutBtn() {
        userDropdown.click();
        logoutBtn.click();
    }

    public String getTxtDashboard() {
        return txtDashboard.getText();
    }
}