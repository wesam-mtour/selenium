package com.exalt.tests.UItest.settings;

import com.exalt.infra.UItest.actions.Actionsf;

import com.exalt.pom.conduitpages.ConduitHomePage;
import com.exalt.pom.conduitpages.ConduitLoginPage;
import com.exalt.pom.conduitpages.ConduitProfilePage;
import com.exalt.pom.conduitpages.ConduitSettingsPage;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.testng.annotations.*;

import static com.exalt.infra.utils.Constants.*;

public class ChangePassToInValidPass extends BaseSettings {

    @BeforeClass
    @Parameters("browser")
    protected void setup(String browser) throws Exception {
        super.setup(browser);
        webDriver = BrowserFactory.startWebDriver(browser);
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitSettingsPage = new ConduitSettingsPage(webDriver);
        conduitProfilePage = new ConduitProfilePage(webDriver);
        BrowserFactory.openUrl(WEB_DRIVER_URL);
    }

    @BeforeMethod
    protected void beforeMethod() {
        super.beforeMethod();
        conduitLoginPage.logIn();
        Actionsf.waitTitleToBe(HOME_PAGE);
        conduitHomePage.clickSettingsLink();
    }

    @AfterMethod
    protected void afterMethod() {
        super.afterMethod();
        conduitHomePage.clickSettingsLink();
        conduitSettingsPage.changePassword("123456789");
        Actionsf.waitTitleToBe(USER_PAGE);
        conduitHomePage.clickSettingsLink();
        conduitSettingsPage.clickHereToLogoutButton();
        Actionsf.waitTitleToBe(HOME_PAGE);
        conduitHomePage.clickSignInLink();
        Actionsf.waitTitleToBe(SIGN_IN_PAGE);
    }

    @AfterClass
    protected void afterClass() {
        super.afterClass();
        webDriver.quit();
    }

    public void ch_OldPassToInvalidPasswordTest(String email, String newPassword, String errorMessage) {
        conduitSettingsPage.changePassword(newPassword);
        Actionsf.assertTrue(Actionsf.isDisplayed(conduitSettingsPage.errorMessage));
        Actionsf.assertTrue(webDriver.getTitle().equals(SETTINGS_PAGE));
        Actionsf.assertEquals(Actionsf.getText(conduitSettingsPage.errorMessage), errorMessage);
        /*
         test verification
         */
        conduitSettingsPage.clickHereToLogoutButton();
        Actionsf.waitTitleToBe(HOME_PAGE);
        conduitHomePage.clickSignInLink();
        Actionsf.waitTitleToBe(SIGN_IN_PAGE);
        conduitLoginPage.logIn(email, newPassword);
        Actionsf.assertTrue(Actionsf.isDisplayed(conduitLoginPage.errorMessage));
        Actionsf.assertTrue(webDriver.getTitle().equals(SIGN_IN_PAGE));
        /*
        To be able to login again with the old password in the event of an error in one of the previous tests
         */
        conduitLoginPage.logIn();
        Actionsf.waitTitleToBe(HOME_PAGE);
    }
}
