package com.exalt.tests.UItest.home;

import com.exalt.infra.UItest.actions.Actionsf;
import com.exalt.pom.conduitpages.*;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Test
public class GlobalFeedTest {

    private WebDriver webDriver;
    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    private ConduitLoginPage conduitLoginPage;
    private ConduitHomePage conduitHomePage;
    private ConduitGlobalFeedPage conduitGlobalFeedPage;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser);
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitGlobalFeedPage = new ConduitGlobalFeedPage(webDriver);
        BrowserFactory.openUrl(WEB_DRIVER_URL);
        conduitLoginPage.logIn();
    }

    @AfterClass
    void tearDown() {
        webDriver.quit();
    }

    public void ConduitLikeTest() {
        conduitHomePage.clickGlobalFeedLink();
        String oldValue = Actionsf.getText(conduitGlobalFeedPage.numberOfLikes);
        conduitGlobalFeedPage.clickLikeButton();
        /*
         test verification, number of like must be increased by 1 after doing like
         */
        String newValue = Actionsf.getText(conduitGlobalFeedPage.numberOfLikes, String.valueOf(Integer.valueOf(oldValue) + 1));
        Actionsf.assertEquals(Integer.valueOf(newValue), (Integer.valueOf(oldValue) + 1));
        conduitGlobalFeedPage.clickLikeButton();
        /*
         test verification, number of like must be decreased by 1, after doing unlike
         */
        newValue = Actionsf.getText(conduitGlobalFeedPage.numberOfLikes, String.valueOf(Integer.valueOf(oldValue)));
        Actionsf.assertEquals(Integer.valueOf(newValue), (Integer.valueOf(oldValue)));
    }
}
