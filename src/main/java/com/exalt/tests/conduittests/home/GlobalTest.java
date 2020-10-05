package com.exalt.tests.conduittests.home;

import com.exalt.infra.actions.ActionsFinder;
import com.exalt.pom.conduitpages.*;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

@Test
public class GlobalTest {

    private WebDriver webDriver;
    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    private ConduitLoginPage conduitLoginPage;
    private ConduitHomePage conduitHomePage;
    private ConduitGlobalFeedPage conduitGlobalFeedPage;
    private WebDriverWait wait;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser);
        BrowserFactory.openUrl(WEB_DRIVER_URL);
        wait = new WebDriverWait(webDriver, 5);

        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitGlobalFeedPage = new ConduitGlobalFeedPage(webDriver);
        conduitLoginPage.logIn();
    }

    @AfterMethod
    void tearDown() {
        webDriver.quit();
    }

    public void ConduitLikeTest() {
        conduitHomePage.clickGlobalFeedLink();
        String oldValue = ActionsFinder.getText(conduitGlobalFeedPage.numberOfLikes);
        conduitGlobalFeedPage.clickLikeButton();
        String newValue = ActionsFinder.getText(conduitGlobalFeedPage.numberOfLikes, String.valueOf(Integer.valueOf(oldValue) + 1));
        ActionsFinder.assertEquals(Integer.valueOf(newValue), (Integer.valueOf(oldValue) + 1));
        conduitGlobalFeedPage.clickLikeButton();
        newValue = ActionsFinder.getText(conduitGlobalFeedPage.numberOfLikes, String.valueOf(Integer.valueOf(oldValue)));
        ActionsFinder.assertEquals(Integer.valueOf(newValue), (Integer.valueOf(oldValue)));

    }
}
