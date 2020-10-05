package com.exalt.tests.conduittests.settings;

import com.exalt.infra.actions.ActionsFinder;
import com.exalt.infra.dataprovider.DataProviderFinder;
import com.exalt.pom.conduitpages.ConduitHomePage;
import com.exalt.pom.conduitpages.ConduitLoginPage;
import com.exalt.pom.conduitpages.ConduitProfilePage;
import com.exalt.pom.conduitpages.ConduitUserSettingsPage;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.exalt.infra.utils.Constants.*;

@Test(dataProvider = "Excel", dataProviderClass = DataProviderFinder.class)
public class ChangePasswordTest {
    private WebDriver webDriver;
    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    private ConduitLoginPage conduitLoginPage;
    private ConduitHomePage conduitHomePage;
    private ConduitUserSettingsPage conduitUserSettingsPage;
    private ConduitProfilePage conduitProfilePage;
    private String testingErrorMessage = "email or password is invalid";
    private WebDriverWait wait;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser);
        BrowserFactory.openUrl(WEB_DRIVER_URL);
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitUserSettingsPage = new ConduitUserSettingsPage(webDriver);
        conduitProfilePage = new ConduitProfilePage(webDriver);
        wait = new WebDriverWait(webDriver, 5);
    }

    @AfterMethod
    void tearDown() {
        webDriver.quit();
    }

    public void ch_OldPassToInvalidPasswordTest(String email, String newPassword, String errorMessage) throws InterruptedException {
        conduitLoginPage.logIn();
        ActionsFinder.waitTitleToBe(HOME_PAGE, wait);
        conduitHomePage.clickSettingsLink();
        conduitUserSettingsPage.changeOldPasswordToInvalidPassword(newPassword);
        ActionsFinder.assertTrue(webDriver.getTitle().equals(SETTINGS_PAGE));
        ActionsFinder.assertEquals(ActionsFinder.getText(conduitUserSettingsPage.errorMessage), errorMessage);
        conduitUserSettingsPage.clickHereToLogoutButton();
        conduitHomePage.clickSignInLink();
        conduitLoginPage.logIn(email, newPassword);
        ActionsFinder.assertTrue(webDriver.getTitle().equals(SIGN_IN_PAGE));
    }

    public void ch_OldPassToValidPasswordTest(String email, String oldPassword, String newPassword) throws InterruptedException {
        conduitLoginPage.logIn(email, oldPassword);
        ActionsFinder.waitTitleToBe(HOME_PAGE, wait);
        conduitHomePage.clickSettingsLink();
        conduitUserSettingsPage.changeOldPasswordToValidPassword(newPassword);
        ActionsFinder.waitTitleToBe("@" + ActionsFinder.getText(conduitUserSettingsPage.userProfileLink) + " — Conduit", wait);
        conduitProfilePage.clickSettingsLink();
        conduitUserSettingsPage.clickHereToLogoutButton();
        conduitHomePage.clickSignInLink();
        conduitLoginPage.logIn(email, newPassword);
        ActionsFinder.waitTitleToBe(HOME_PAGE, wait);
    }
}
