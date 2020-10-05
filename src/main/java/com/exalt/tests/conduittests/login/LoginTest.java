package com.exalt.tests.conduittests.login;

import com.exalt.infra.actions.ActionsFinder;
import com.exalt.infra.dataprovider.DataProviderFinder;
import com.exalt.pom.conduitpages.ConduitLoginPage;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static com.exalt.infra.utils.Constants.HOME_PAGE;
import static com.exalt.infra.utils.Constants.SIGN_IN_PAGE;

@Test(dataProvider = "Excel", dataProviderClass = DataProviderFinder.class)
public class LoginTest {
    private WebDriver webDriver;
    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    private ConduitLoginPage conduitLoginPage;
    private WebDriverWait wait;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser);
        BrowserFactory.openUrl(WEB_DRIVER_URL);
        conduitLoginPage = new ConduitLoginPage(webDriver);
        wait = new WebDriverWait(webDriver, 5);
    }

    @AfterMethod
    void tearDown() {
        webDriver.quit();
    }

    @Test(enabled = false)
    public void loginWithInvalidCredentialsTest(String email, String password, String expectedErrorMessage) {
        conduitLoginPage.logIn(email, password);
        ActionsFinder.isDisplayed(conduitLoginPage.errorMessage);
        ActionsFinder.assertEquals(ActionsFinder.getText(conduitLoginPage.errorMessage), expectedErrorMessage);
        ActionsFinder.assertEquals(webDriver.getTitle(), SIGN_IN_PAGE);
    }

    public void loginWithValidCredentialsTest(String email, String password) {
        conduitLoginPage.logIn(email, password);
        /*
        need to verify the title to pass the test
         */
        String s = ActionsFinder.getText(conduitLoginPage.titlee);

         ActionsFinder.getText(conduitLoginPage.titlee,SIGN_IN_PAGE);
        //  ActionsFinder.waitTitleToBe(HOME_PAGE);
    }
}
