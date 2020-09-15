package com.exalt.tests;

import com.exalt.pom.BrowserFactory;

import com.exalt.pom.RitajLoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RitajLoginTest {

    private WebDriver webDriver;
    private RitajLoginPage ritajLoginPage;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\WesamM\\Downloads\\chromedriver.exe");
        webDriver = BrowserFactory.startWebDriver("chrome", "https://ritaj.birzeit.edu/register/?return%5furl=%2f");
    }

    @Test
    public void UserLoginTest() {
        ritajLoginPage = new RitajLoginPage(webDriver);
        ritajLoginPage.loginWithNameAndPass("1160508", "radeee19867");
        webDriver.quit();
    }
}
