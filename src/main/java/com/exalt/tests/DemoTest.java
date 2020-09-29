package com.exalt.tests;

import com.exalt.dataproviderinfra.DataProviderFinder;
import com.exalt.webdriverinitializer.BrowserFactory;
import com.exalt.pom.ConduitLoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.*;

public class DemoTest {
    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    private WebDriver webDriver;
    private ConduitLoginPage conduitLoginPage;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser, WEB_DRIVER_URL);
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }

}


