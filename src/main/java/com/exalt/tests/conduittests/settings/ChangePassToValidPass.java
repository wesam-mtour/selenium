package com.exalt.tests.conduittests.settings;

import com.exalt.infra.actions.Actionsf;
import com.exalt.infra.dataprovider.ExcelDataProvider;
import com.exalt.pom.conduitpages.ConduitHomePage;
import com.exalt.pom.conduitpages.ConduitLoginPage;
import com.exalt.pom.conduitpages.ConduitProfilePage;
import com.exalt.pom.conduitpages.ConduitSettingsPage;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.exalt.infra.utils.Constants.HOME_PAGE;
import static com.exalt.infra.utils.Constants.USER_PAGE;


@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
public class ChangePassToValidPass extends BaseSettings {

    @BeforeMethod
    @Parameters("browser")
    protected void setup(String browser) throws Exception {
        super.setup(browser);
        webDriver = BrowserFactory.startWebDriver(browser);
        wait = new WebDriverWait(webDriver, TIME_OUT);
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitSettingsPage = new ConduitSettingsPage(webDriver);
        conduitProfilePage = new ConduitProfilePage(webDriver);
        BrowserFactory.openUrl(WEB_DRIVER_URL);
        conduitLoginPage.logIn();
        Actionsf.waitTitleToBe(HOME_PAGE, wait);
        conduitHomePage.clickSettingsLink();
    }


    @AfterMethod
    protected void afterMethod() {
        super.afterMethod();
        webDriver.quit();
    }

    public void ch_OldPassToValidPasswordTest(String email, String newPassword) {
        conduitSettingsPage.changePassword(newPassword);
        Actionsf.waitTitleToBe(USER_PAGE, wait);
        /*
         test verification
         */
        conduitProfilePage.clickSettingsLink();
        conduitSettingsPage.clickHereToLogoutButton();
        conduitHomePage.clickSignInLink();
        conduitLoginPage.logIn(email, newPassword);
        Actionsf.waitTitleToBe(HOME_PAGE, wait);
        /*
        To be able to log in again with the old password in the event of an error in one of the previous tests
         */
        conduitHomePage.clickSettingsLink();
        conduitSettingsPage.changePassword("123456789");
        conduitSettingsPage.clickHereToLogoutButton();
        Actionsf.waitTitleToBe(HOME_PAGE, wait);
    }
}
