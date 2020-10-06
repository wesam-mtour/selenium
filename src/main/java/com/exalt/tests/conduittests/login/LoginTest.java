package com.exalt.tests.conduittests.login;

import com.exalt.infra.actions.Actionsf;
import com.exalt.infra.dataprovider.ExcelDataProvider;
import com.exalt.pom.conduitpages.ConduitHomePage;
import com.exalt.pom.conduitpages.ConduitLoginPage;
import com.exalt.pom.conduitpages.ConduitUserSettingsPage;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static com.exalt.infra.utils.Constants.HOME_PAGE;
import static com.exalt.infra.utils.Constants.SIGN_IN_PAGE;

@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
public class LoginTest {
    private WebDriver webDriver;
    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    private ConduitLoginPage conduitLoginPage;
    private ConduitHomePage conduitHomePage;
    private ConduitUserSettingsPage conduitUserSettingsPage;
    private WebDriverWait wait;


    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser);
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitUserSettingsPage = new ConduitUserSettingsPage(webDriver);
        BrowserFactory.openUrl(WEB_DRIVER_URL);

    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
        Actionsf.clear(conduitLoginPage.email);
        Actionsf.clear(conduitLoginPage.password);
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        webDriver.quit();
    }

    @Test(enabled = false)
    public void loginWithInvalidCredentialsTest(String email, String password, String expectedErrorMessage) throws InterruptedException {
        conduitLoginPage.logIn(email, password);
        Actionsf.isDisplayed(conduitLoginPage.errorMessage);
        Actionsf.assertEquals(Actionsf.getText(conduitLoginPage.errorMessage), expectedErrorMessage);
        Actionsf.assertEquals(webDriver.getTitle(), SIGN_IN_PAGE);
    }

    public void loginWithValidCredentialsTest(String email, String password) throws InterruptedException {
        conduitLoginPage.logIn(email, password);
        Actionsf.waitTitleToBe(HOME_PAGE);
        conduitHomePage.clickSettingsLink();
        conduitUserSettingsPage.clickHereToLogoutButton();
        conduitHomePage.clickSignInLink();
    }
}
