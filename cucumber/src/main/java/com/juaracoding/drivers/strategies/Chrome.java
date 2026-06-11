package com.juaracoding.drivers.strategies;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome implements DriverStrategy {

    @Override
    public WebDriver setStrategy() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension",false);
        options.addArguments("--no-sandbox");
        return new ChromeDriver(options);
    }
}
