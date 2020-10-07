package com.exalt.tests.conduittests.article;

import com.exalt.pom.conduitpages.*;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseArticle {

    protected WebDriver webDriver;
    protected final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    protected ConduitLoginPage conduitLoginPage;
    protected  ConduitHomePage conduitHomePage;
    protected ConduitProfilePage conduitProfilePage;
    protected ConduitArticlePreviewPage conduitArticlePreviewPage;
    protected  ConduitNewArticlePage conduitNewArticlePage;

    @BeforeClass
    @Parameters("browser")
    protected  void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser);
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitProfilePage = new ConduitProfilePage(webDriver);
        conduitArticlePreviewPage = new ConduitArticlePreviewPage(webDriver);
        conduitNewArticlePage = new ConduitNewArticlePage(webDriver);
        BrowserFactory.openUrl(WEB_DRIVER_URL);
        conduitLoginPage.logIn();
    }

    @AfterClass
    protected void tearDown() {
        webDriver.quit();
    }


}
