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
        //conduitLoginPage.logIn();
    }

    @AfterMethod
    void tearDown() {
        webDriver.quit();
    }

    @Test(enabled = false, dataProvider = "Excel", dataProviderClass = DataProviderFinder.class)
    void ch_OldPassToInvalidPasswordTest(String email, String newPassword, String errorMessage) throws InterruptedException {
        conduitHomePage.getSettingsLink();
        conduitUserSettingsPage.changeOldPasswordToInvalidPassword(newPassword);
        ActionsFinder.assertTrue(webDriver.getTitle().equals(SETTINGS_PAGE));
        ActionsFinder.assertEquals(conduitUserSettingsPage.getErrorMessage().getText(), errorMessage);
        conduitUserSettingsPage.clickHereToLogoutButton();
        conduitHomePage.clickSignInLink();
        conduitLoginPage.loginWithInvalidCredentials(email, newPassword, testingErrorMessage);
        ActionsFinder.assertTrue(webDriver.getTitle().equals(SIGN_IN_PAGE));
        ActionsFinder.assertEquals(conduitLoginPage.getErrorMessage().getText(), testingErrorMessage);
    }

    @Test(enabled = false, dataProvider = "Excel", dataProviderClass = DataProviderFinder.class)
    void ch_OldPassToValidPasswordTest(String email, String oldPassword, String newPassword) throws InterruptedException {
        conduitLoginPage.logIn(email,oldPassword);
        conduitHomePage.getSettingsLink();
        conduitUserSettingsPage.changeOldPasswordToValidPassword(newPassword);
        ActionsFinder.waitTitleToBe("@" + ActionsFinder.getText(conduitUserSettingsPage.getUserProfileLink()) + " — Conduit", wait);
        conduitProfilePage.clickSettingsLink();
        conduitUserSettingsPage.clickHereToLogoutButton();
        conduitHomePage.clickSignInLink();
        conduitLoginPage.loginWithValidCredentials(email, newPassword);
        ActionsFinder.waitTitleToBe(HOME_PAGE, wait);
      //conduitLoginPage.setTestingPassword(newPassword);

    }
}
