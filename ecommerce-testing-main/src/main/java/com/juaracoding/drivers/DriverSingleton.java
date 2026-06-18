package com.juaracoding.drivers;

import com.epam.healenium.SelfHealingDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Locale;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSingleton {
    private static WebDriver driver;

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        ensureLogDirectory();

        if (driver == null) {
            driver = createWebDriver();
            driver = SelfHealingDriver.create(driver);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    private static void ensureLogDirectory() {
        java.nio.file.Path logPath = java.nio.file.Paths.get("log");
        if (java.nio.file.Files.notExists(logPath)) {
            try {
                java.nio.file.Files.createDirectories(logPath);
            } catch (java.io.IOException e) {
                throw new RuntimeException("Gagal membuat folder log: " + logPath.toAbsolutePath(), e);
            }
        }
    }

    private static WebDriver createWebDriver() {
        String browser = System.getProperty("browser", "firefox-headless").trim().toLowerCase(Locale.ROOT);
        String gridUrl = System.getProperty("selenium.grid.url", "http://localhost:4444/wd/hub").trim();
        String mode = System.getProperty("mode", "remote").trim().toLowerCase(Locale.ROOT);

        if ("local" .equals(mode)) {
            throw new IllegalStateException("Local mode tanpa WebDriverManager belum didukung; gunakan mode=remote dengan Selenium Grid atau tambahkan WebDriverManager lagi.");
        }

        try {
            switch (browser) {
                case "chrome-headless": {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new");
                    options.addArguments("--window-size=1366,768");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--disable-infobars");
                    return new RemoteWebDriver(new URL(gridUrl), options);
                }
                case "firefox": {
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("--width=1366");
                    options.addArguments("--height=768");
                    return new RemoteWebDriver(new URL(gridUrl), options);
                }
                case "firefox-headless": {
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("-headless");
                    options.addArguments("--width=1366");
                    options.addArguments("--height=768");
                    return new RemoteWebDriver(new URL(gridUrl), options);
                }
                case "chrome":
                default: {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--disable-infobars");
                    return new RemoteWebDriver(new URL(gridUrl), options);
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL Selenium Grid tidak valid: " + gridUrl, e);
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}