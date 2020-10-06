package com.exalt.tests.conduittests.article;

import com.exalt.infra.actions.Actionsf;
import com.exalt.pom.conduitpages.*;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static com.exalt.infra.utils.Constants.*;

@Test
public class ArticleTest {
    private WebDriver webDriver;
    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    private ConduitLoginPage conduitLoginPage;
    private ConduitHomePage conduitHomePage;
    private ConduitProfilePage conduitProfilePage;
    private ConduitArticlePreviewPage conduitArticlePreviewPage;
    private ConduitNewArticlePage conduitNewArticlePage;
    private String title = "this title created for testing";
    private String articleAbout = "this description created for testing";
    private String body = "this body created for testing";
    private String endTag = "this eng Tag created for testing";

    private String testingErrorMessage = "email or password is invalid";

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser);
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitProfilePage = new ConduitProfilePage(webDriver);
        conduitArticlePreviewPage = new ConduitArticlePreviewPage(webDriver);
        conduitNewArticlePage = new ConduitNewArticlePage(webDriver);
        BrowserFactory.openUrl(WEB_DRIVER_URL);
        conduitLoginPage.logIn();
    }

    @BeforeMethod
    public void beforeMethod() {
        conduitHomePage.clickNewArticleLink();
        Actionsf.waitTitleToBe(EDITOR_PAGE);
        conduitNewArticlePage.postNewArticle(title, articleAbout, body, endTag);
    }

    @AfterClass
    void tearDown() {
        webDriver.quit();
    }

    public void deleteArticleTest() throws InterruptedException {
//        conduitHomePage.clickUserProfileLink();
//        conduitProfilePage.clickArticlePreview();
        conduitArticlePreviewPage.clickDeleteArticleButton();
        Actionsf.waitTitleToBe(HOME_PAGE);
        conduitHomePage.clickProfileLink();
        Actionsf.waitTitleToBe(USER_PAGE);
        Actionsf.assertNotEquals(conduitProfilePage.articleTitle.getText(),title);
        /*

        not completed need ver
         */

    }
//
//    @Test(enabled = false, dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
//    public void postNewArticleTest(String title, String articleAbout, String body, String endTag) throws InterruptedException {
//        conduitHomePage.clickNewArticleLink();
//        Actionsf.waitTitleToBe(EDITOR_PAGE);
//        conduitNewArticlePage.postNewArticle(title, articleAbout, body, endTag);
//        Actionsf.waitTitleToBe(title + " — Conduit");
//    }
}
