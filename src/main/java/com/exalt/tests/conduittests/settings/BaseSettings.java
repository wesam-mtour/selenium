package com.exalt.tests.conduittests.settings;

import com.exalt.infra.actions.Actionsf;
import com.exalt.infra.dataprovider.ExcelDataProvider;
import com.exalt.pom.conduitpages.ConduitHomePage;
import com.exalt.pom.conduitpages.ConduitLoginPage;
import com.exalt.pom.conduitpages.ConduitProfilePage;
import com.exalt.pom.conduitpages.ConduitSettingsPage;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static com.exalt.infra.utils.Constants.HOME_PAGE;
import static com.exalt.infra.utils.Constants.USER_PAGE;


@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
public class BaseSettings {

    protected WebDriver webDriver;
    protected final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    protected ConduitLoginPage conduitLoginPage;
    protected ConduitHomePage conduitHomePage;
    protected ConduitSettingsPage conduitSettingsPage;
    protected ConduitProfilePage conduitProfilePage;
    protected WebDriverWait wait;
    private final int TIME_OUT = 5;

    @BeforeMethod
    @Parameters("browser")
    protected void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser);
        wait = new WebDriverWait(webDriver, TIME_OUT);
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitSettingsPage = new ConduitSettingsPage(webDriver);
        conduitProfilePage = new ConduitProfilePage(webDriver);
        BrowserFactory.openUrl(WEB_DRIVER_URL);
        conduitLoginPage.logIn();
        Actionsf.waitTitleToBe(HOME_PAGE,wait);
        conduitHomePage.clickSettingsLink();
    }

    @AfterMethod
    protected void afterMethod() {
        conduitHomePage.clickSettingsLink();
        conduitSettingsPage.changePassword("123456789");
        conduitSettingsPage.clickHereToLogoutButton();
        Actionsf.waitTitleToBe(USER_PAGE, wait);
        webDriver.quit();
    }
}

