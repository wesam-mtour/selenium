package com.exalt.tests.UItest.article;

import com.exalt.pom.conduitpages.*;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Test
public class BaseArticle {

    protected WebDriver webDriver;
    protected final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    protected ConduitLoginPage conduitLoginPage;
    protected ConduitHomePage conduitHomePage;
    protected ConduitProfilePage conduitProfilePage;
    protected ConduitArticlePreviewPage conduitArticlePreviewPage;
    protected ConduitNewArticlePage conduitNewArticlePage;
    protected ConduitSettingsPage conduitSettingsPage;
    protected ConduitGlobalFeedPage conduitGlobalFeedPage;

    @BeforeClass
    @Parameters("browser")
    protected void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser);
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitProfilePage = new ConduitProfilePage(webDriver);
        conduitArticlePreviewPage = new ConduitArticlePreviewPage(webDriver);
        conduitNewArticlePage = new ConduitNewArticlePage(webDriver);
        conduitSettingsPage = new ConduitSettingsPage(webDriver);
        conduitGlobalFeedPage = new ConduitGlobalFeedPage(webDriver);
        BrowserFactory.openUrl(WEB_DRIVER_URL);
        conduitLoginPage.logIn();
    }

    protected void beforeMethod() {

    }

    protected void afterMethod() {

    }

    @AfterClass
    protected void tearDown() {
        webDriver.quit();
    }


}
