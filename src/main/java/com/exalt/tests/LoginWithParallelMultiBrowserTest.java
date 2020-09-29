package com.exalt.tests;


import com.exalt.webdriverinitializer.BrowserFactory;

import com.exalt.pom.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class LoginWithParallelMultiBrowserTest {


    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/";
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
        loginPage.loginWithNameAndPass("wesam@wesam.com", "wesam");
    }

    @AfterTest
    public void tearDown() {
        webDriver.quit();

    }
}
