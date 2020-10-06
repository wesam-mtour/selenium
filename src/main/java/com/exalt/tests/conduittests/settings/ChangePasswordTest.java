package com.exalt.tests.conduittests.settings;

import com.exalt.infra.actions.Actionsf;
import com.exalt.infra.dataprovider.ExcelDataProvider;
import com.exalt.pom.conduitpages.ConduitHomePage;
import com.exalt.pom.conduitpages.ConduitLoginPage;
import com.exalt.pom.conduitpages.ConduitProfilePage;
import com.exalt.pom.conduitpages.ConduitUserSettingsPage;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static com.exalt.infra.utils.Constants.*;

@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
public class ChangePasswordTest {
    private WebDriver webDriver;
    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    private ConduitLoginPage conduitLoginPage;
    private ConduitHomePage conduitHomePage;
    private ConduitUserSettingsPage conduitUserSettingsPage;
    private ConduitProfilePage conduitProfilePage;
    private String testingErrorMessage = "email or password is invalid";

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser);
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitUserSettingsPage = new ConduitUserSettingsPage(webDriver);
        conduitProfilePage = new ConduitProfilePage(webDriver);
        BrowserFactory.openUrl(WEB_DRIVER_URL);
    }

    @AfterMethod
    public void afterMethod() {
        Actionsf.clear(conduitLoginPage.email);
        Actionsf.clear(conduitLoginPage.password);
    }

    @AfterClass
    void tearDown() {
        webDriver.quit();
    }

    @Test(enabled = false)
    public void ch_OldPassToInvalidPasswordTest(String email, String newPassword, String errorMessage) throws InterruptedException {
        conduitLoginPage.logIn();
        Actionsf.waitTitleToBe(HOME_PAGE);
        conduitHomePage.clickSettingsLink();
        conduitUserSettingsPage.changeOldPasswordToInvalidPassword(newPassword);
        Actionsf.assertTrue(webDriver.getTitle().equals(SETTINGS_PAGE));
        Actionsf.assertEquals(Actionsf.getText(conduitUserSettingsPage.errorMessage), errorMessage);
        conduitUserSettingsPage.clickHereToLogoutButton();
        conduitHomePage.clickSignInLink();
        conduitLoginPage.logIn(email, newPassword);
        Actionsf.assertTrue(webDriver.getTitle().equals(SIGN_IN_PAGE));
    }


    public void ch_OldPassToValidPasswordTest(String email, String oldPassword, String newPassword) throws InterruptedException {
        conduitLoginPage.logIn(email, oldPassword);
        Actionsf.waitTitleToBe(HOME_PAGE);
        conduitHomePage.clickSettingsLink();
        conduitUserSettingsPage.changeOldPasswordToValidPassword(newPassword);
        Actionsf.waitTitleToBe("@" + Actionsf.getText(conduitUserSettingsPage.userProfileLink) + " — Conduit");
        conduitProfilePage.clickSettingsLink();
        conduitUserSettingsPage.clickHereToLogoutButton();
        conduitHomePage.clickSignInLink();
        conduitLoginPage.logIn(email, newPassword);
        Actionsf.waitTitleToBe(HOME_PAGE);
        conduitHomePage.clickSettingsLink();
        conduitUserSettingsPage.clickHereToLogoutButton();
        conduitHomePage.clickSignInLink();
    }
}
