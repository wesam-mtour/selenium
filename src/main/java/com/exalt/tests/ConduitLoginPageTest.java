package com.exalt.tests;

import com.exalt.dataproviderinfra.DataProviderFinder;
import com.exalt.webdriverinitializer.BrowserFactory;
import com.exalt.pom.ConduitLoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.*;

public class ConduitLoginPageTest {
    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    private WebDriver webDriver;
    private ConduitLoginPage conduitLoginPage;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser, WEB_DRIVER_URL);
    }

    @Test(dataProvider = "Excel", dataProviderClass = DataProviderFinder.class)
    public void ConduitLoginTest(String run, String email, String password) throws InterruptedException {
        if (run.equals("no")) {
            throw new SkipException("Skipping tests because resource was not available.");
        } else {
            conduitLoginPage = new ConduitLoginPage(webDriver);
            conduitLoginPage.loginWithEmailAndPassword(email, password);
        }
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }
}


