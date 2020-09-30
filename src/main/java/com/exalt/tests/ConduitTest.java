package com.exalt.tests;

import com.exalt.infra.dataprovider.DataProviderFinder;
import com.exalt.pom.conduitpages.*;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.*;

public class ConduitTest {
    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    private WebDriver webDriver;
    private ConduitLoginPage conduitLoginPage;
    private ConduitNewArticlePage conduitNewArticlePage;
    private ConduitGlobalFeedPage conduitGlobalFeedPage;
    private ConduitProfilePage conduitProfilePage;
    private ConduitHomePage conduitHomePage;
    private ConduitArticlePreviewPage conduitArticlePreviewPage;
    private ConduitUserSettings conduitUserSettings;

    private String testingEmail = "wiasm.mtour@gmail.com";
    private String testingPassword = "11225544";


    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser, WEB_DRIVER_URL);
    }

    @AfterMethod
    public void tearDown() {
        webDriver.close();
        webDriver.quit();
    }

    @Test(enabled = false, dataProvider = "Excel", dataProviderClass = DataProviderFinder.class)
    public void ConduitLoginTest(String run, String email, String password) throws InterruptedException {
        if (run.equals("no")) {
            throw new SkipException("Skipping tests because resource was not available.");
        } else {
            conduitLoginPage = new ConduitLoginPage(webDriver);
            conduitLoginPage.loginWithEmailAndPassword(email, password);
        }
    }

    @Test(enabled = false, dataProvider = "Excel", dataProviderClass = DataProviderFinder.class)
    void ConduitPostNewArticleTest(String run, String title, String articleAbout, String body, String endTag) throws InterruptedException {
        if (run.equals("no")) {
            throw new SkipException("Skipping tests because resource was not available.");
        } else {
            conduitLoginPage = new ConduitLoginPage(webDriver);
            conduitLoginPage.loginWithEmailAndPassword(testingEmail, testingPassword);
            conduitHomePage = new ConduitHomePage(webDriver);
            conduitHomePage.getNewArticleLink();
            conduitNewArticlePage = new ConduitNewArticlePage(webDriver);
            conduitNewArticlePage.postNewArticle(title, articleAbout, body, endTag);
        }
    }

    @Test(enabled = false)
    void ConduitLikeTest() throws InterruptedException {
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitLoginPage.loginWithEmailAndPassword(testingEmail, testingPassword);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitHomePage.getGlobalFeedLink();
        conduitGlobalFeedPage = new ConduitGlobalFeedPage(webDriver);
        conduitGlobalFeedPage.applyLike();
        conduitGlobalFeedPage.applyDisLike();
    }

    @Test(enabled = false)
    void ConduitDeleteArticleTest() throws InterruptedException {
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitLoginPage.loginWithEmailAndPassword(testingEmail, testingPassword);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitHomePage.getUserProfileLink();
        conduitProfilePage = new ConduitProfilePage(webDriver);
        conduitProfilePage.getArticlePreview();

        conduitArticlePreviewPage = new ConduitArticlePreviewPage(webDriver);
        conduitArticlePreviewPage.deleteArticle();
    }

    @Test(enabled = true)
    void ConduitSetNewPasswordTest() throws InterruptedException {
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitLoginPage.loginWithEmailAndPassword(testingEmail, testingPassword);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitHomePage.getSettingsLink();
        conduitUserSettings = new ConduitUserSettings(webDriver);
        conduitUserSettings.setNewPassword("123456789");
    }
}


