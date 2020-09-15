package com.exalt.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

    private static WebDriver webDriver;

    public static WebDriver startWebDriver(String browserName, String url) {
        if (browserName.equalsIgnoreCase("chrome")) {
            webDriver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            webDriver = new FirefoxDriver();
        }
        webDriver.get(url);
        return webDriver;
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public static void setWebDriver(WebDriver webDriver) {
        BrowserFactory.webDriver = webDriver;
    }
}
