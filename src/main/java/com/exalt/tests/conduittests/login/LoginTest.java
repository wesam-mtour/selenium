package com.exalt.tests.conduittests.login;

import com.exalt.infra.dataprovider.DataProviderFinder;
import com.exalt.pom.conduitpages.ConduitLoginPage;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver webDriver;
    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    private ConduitLoginPage conduitLoginPage;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser);
        BrowserFactory.openUrl(WEB_DRIVER_URL);
        conduitLoginPage = new ConduitLoginPage(webDriver);
    }
    @AfterMethod void tearDown(){
        webDriver.quit();
    }

    @Test(enabled = false, dataProvider = "Excel", dataProviderClass = DataProviderFinder.class)
    public void LoginWithInvalidCredentialsTest(String email, String password, String expectedErrorMessage) {
        conduitLoginPage.loginWithInvalidCredentials(email, password, expectedErrorMessage);
    }

    @Test(enabled = false, dataProvider = "Excel", dataProviderClass = DataProviderFinder.class)
    public void LoginWithValidCredentialsTest(String email, String password) throws InterruptedException {
        conduitLoginPage.loginWithValidCredentials(email, password);
    }
}
