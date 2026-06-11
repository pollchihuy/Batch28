package com.juaracoding.drivers.strategies;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Firefox implements DriverStrategy {

    @Override
    public WebDriver setStrategy() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}
