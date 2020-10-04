//package com.exalt.tests;
//
//import com.exalt.infra.dataprovider.DataProviderFinder;
//import com.exalt.pom.conduitpages.*;
//import com.exalt.webdriverinitializer.BrowserFactory;
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.*;
//
//public class ConduitTest {
//    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
//    private WebDriver webDriver;
//    private ConduitLoginPage conduitLoginPage;
//    private ConduitNewArticlePage conduitNewArticlePage;
//    private ConduitGlobalFeedPage conduitGlobalFeedPage;
//    private ConduitProfilePage conduitProfilePage;
//    private ConduitHomePage conduitHomePage;
//    private ConduitArticlePreviewPage conduitArticlePreviewPage;
//    private ConduitUserSettingsPage conduitUserSettingsPage;
//
//    private String testingEmail = "wiasm.mtour@gmail.com";
//    private String testingPassword = "123456789";
//    private String testingErrorMessage = "email or password is invalid";
//
//
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
//
//    @BeforeMethod
//    public void setup() throws Exception {
//        BrowserFactory.openUrl(WEB_DRIVER_URL);
//      /*  conduitLoginPage = new ConduitLoginPage(webDriver);
//        conduitHomePage = new ConduitHomePage(webDriver);
//        conduitNewArticlePage = new ConduitNewArticlePage(webDriver);
//        conduitGlobalFeedPage = new ConduitGlobalFeedPage(webDriver);
//        conduitProfilePage = new ConduitProfilePage(webDriver);
//        conduitArticlePreviewPage = new ConduitArticlePreviewPage(webDriver);
//        conduitUserSettingsPage = new ConduitUserSettingsPage(webDriver);*/
//      //  conduitLoginPage.logIn(testingEmail, testingPassword);
//
//    }
//

//
//    @Test(enabled = false, dataProvider = "Excel", dataProviderClass = DataProviderFinder.class)
//    void ConduitPostNewArticleTest(String title, String articleAbout, String body, String endTag) throws InterruptedException {
//        conduitHomePage.clickNewArticleLink();
//        conduitNewArticlePage.postNewArticle(title, articleAbout, body, endTag);
//
//    }
//
//    @Test(enabled = false)
//    void ConduitLikeTest() throws InterruptedException {
//        conduitHomePage.clickGlobalFeedLink();
//        conduitGlobalFeedPage.applyLike();
//        conduitGlobalFeedPage.applyDisLike();
//
//    }
//
//    @Test(enabled = false)
//    void ConduitDeleteArticleTest() throws InterruptedException {
//        conduitHomePage.clickUserProfileLink();
//        conduitProfilePage.clickArticlePreview();
//        conduitArticlePreviewPage.deleteArticle();
//    }
//

//    @Test(enabled = true, dataProvider = "Excel", dataProviderClass = DataProviderFinder.class)
//    void ConduitChangeOldPasswordToValidPasswordTest(String email ,String oldPassword, String newPassword) throws InterruptedException {
//        conduitLoginPage.logIn(email,oldPassword);
//        conduitHomePage.getSettingsLink();
//        conduitUserSettingsPage.changeOldPasswordToValidPassword(newPassword);
//        conduitHomePage.getSettingsLink();
//        conduitUserSettingsPage.clickOrClickHereToLogoutButton();
//        conduitHomePage.clickSignInLink();
//        conduitLoginPage.logIn(email,newPassword);
//    }
//}
//
//
