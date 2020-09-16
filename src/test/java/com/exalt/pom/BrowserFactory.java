package com.exalt.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {

    private static WebDriver webDriver;

    public static WebDriver startWebDriver(String browserName, String url) {
        if (browserName.equalsIgnoreCase("chrome")) {
            webDriver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            webDriver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("IE")) {
            webDriver = new InternetExplorerDriver();
        }
        webDriver.get(url);
        return webDriver;
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }
}