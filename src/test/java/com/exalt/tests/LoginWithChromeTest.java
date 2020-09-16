package com.exalt.tests;

import com.exalt.pom.BrowserFactory;

import com.exalt.pom.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginWithChromeTest {

    final String WEB_DRIVER_KEY = "webdriver.chrome.driver";
    final String WEB_DRIVER_VALUE = "C:\\Users\\wesamM\\Downloads\\chromedriver.exe";
    final String BROWSER_NAME = "chrome";
    final String WEB_DRIVER_URL = "https://senglehardt.com/demo/no_boundaries/loginmanager/";
    private WebDriver webDriver;
    private LoginPage loginPage;

    @BeforeTest
    public void setup() {
        System.setProperty(WEB_DRIVER_KEY, WEB_DRIVER_VALUE);
        webDriver = BrowserFactory.startWebDriver(BROWSER_NAME, WEB_DRIVER_URL);
    }

    @Test
    public void UserLoginTest() {
        loginPage = new LoginPage(webDriver);
        loginPage.loginWithNameAndPass("wesam@wesam.com", "wesam");
        webDriver.quit();
    }
}
