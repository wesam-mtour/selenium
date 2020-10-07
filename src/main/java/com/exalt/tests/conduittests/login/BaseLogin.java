package com.exalt.tests.conduittests.login;

import com.exalt.infra.actions.Actionsf;
import com.exalt.infra.dataprovider.ExcelDataProvider;
import com.exalt.pom.conduitpages.ConduitHomePage;
import com.exalt.pom.conduitpages.ConduitLoginPage;
import com.exalt.pom.conduitpages.ConduitSettingsPage;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
public class BaseLogin {

    protected WebDriver webDriver;
    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    protected  ConduitLoginPage conduitLoginPage;
    protected  ConduitHomePage conduitHomePage;
    protected ConduitSettingsPage conduitSettingsPage;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser);
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitSettingsPage = new ConduitSettingsPage(webDriver);

    }

    @BeforeMethod
    protected  void afterMsdfethod() {
        BrowserFactory.openUrl(WEB_DRIVER_URL);
        BrowserFactory.openUrl("https://github.com/");
        BrowserFactory.openUrl(WEB_DRIVER_URL);


    }

    @AfterMethod
    protected  void afterMethod() {


    }

    @AfterClass
    protected  void tearDown() {
        webDriver.quit();
    }
}
