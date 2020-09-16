package com.exalt.tests;

import com.exalt.infra.ActionsFinder;
import com.exalt.pom.BrowserFactory;

import com.exalt.pom.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginWithParallelMultiBrowserTest {

    final String WEB_DRIVER_URL = "https://senglehardt.com/demo/no_boundaries/loginmanager/";
    private WebDriver webDriver;
    private LoginPage loginPage;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser, WEB_DRIVER_URL);
    }

    @Test
    public void UserLoginTest() throws InterruptedException {
        loginPage = new LoginPage(webDriver);
        loginPage.loginWithNameAndPass("wesam", "wesam");
        webDriver.quit();
    }
}
