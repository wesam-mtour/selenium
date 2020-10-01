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
    private ConduitUserSettingsPage conduitUserSettingsPage;

    private String testingEmail = "wiasm.mtour@gmail.com";
    private String testingPassword = "123456789";
    private String testingErrorMessage = "email or password is invalid";


//    @BeforeClass
//    @Parameters("browser")
//    public void setUpObjects(String browser) throws Exception {
//        webDriver = BrowserFactory.startWebDriver(browser, WEB_DRIVER_URL);
//        conduitLoginPage = new ConduitLoginPage(webDriver);
//        conduitHomePage = new ConduitHomePage(webDriver);
//        conduitNewArticlePage = new ConduitNewArticlePage(webDriver);
//        conduitGlobalFeedPage = new ConduitGlobalFeedPage(webDriver);
//        conduitProfilePage = new ConduitProfilePage(webDriver);
//        conduitArticlePreviewPage = new ConduitArticlePreviewPage(webDriver);
//        conduitUserSettingsPage = new ConduitUserSettingsPage(webDriver);
//    }

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser, WEB_DRIVER_URL);
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitNewArticlePage = new ConduitNewArticlePage(webDriver);
        conduitGlobalFeedPage = new ConduitGlobalFeedPage(webDriver);
        conduitProfilePage = new ConduitProfilePage(webDriver);
        conduitArticlePreviewPage = new ConduitArticlePreviewPage(webDriver);
        conduitUserSettingsPage = new ConduitUserSettingsPage(webDriver);
        conduitLoginPage.logIn(testingEmail, testingPassword);

    }

    @AfterMethod
    public void tearDown() {
        webDriver.close();
        webDriver.quit();
    }

    @Test(enabled = false, dataProvider = "Excel", dataProviderClass = DataProviderFinder.class)
    public void ConduitLoginTest(String email, String password, String expectedErrorMessage) throws InterruptedException {
        conduitLoginPage.loginWithEmailAndPassword(email, password, expectedErrorMessage);
    }

    @Test(enabled = false, dataProvider = "Excel", dataProviderClass = DataProviderFinder.class)
    void ConduitPostNewArticleTest(String title, String articleAbout, String body, String endTag) throws InterruptedException {
        conduitHomePage.getNewArticleLink();
        conduitNewArticlePage.postNewArticle(title, articleAbout, body, endTag);

    }

    @Test(enabled = false)
    void ConduitLikeTest() throws InterruptedException {
        conduitHomePage.getGlobalFeedLink();
        conduitGlobalFeedPage.applyLike();
        conduitGlobalFeedPage.applyDisLike();

    }

    @Test(enabled = false)
    void ConduitDeleteArticleTest() throws InterruptedException {
        conduitHomePage.getUserProfileLink();
        conduitProfilePage.getArticlePreview();
        conduitArticlePreviewPage.deleteArticle();
    }

    @Test(enabled = true, dataProvider = "Excel", dataProviderClass = DataProviderFinder.class)
    void ConduitSetNewPasswordTest(String newPassword, String errorMessage) throws InterruptedException {
        conduitHomePage.getSettingsLink();
        conduitUserSettingsPage.setNewPassword(newPassword, errorMessage);
        conduitHomePage.getSettingsLink();
        conduitUserSettingsPage.getOrClickHereToLogoutButton();
        conduitHomePage.getSignInLink();
        conduitLoginPage.loginWithEmailAndPassword(testingEmail,newPassword,testingErrorMessage);
    }
}


