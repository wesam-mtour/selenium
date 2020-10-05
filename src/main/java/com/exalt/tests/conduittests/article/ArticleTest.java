package com.exalt.tests.conduittests.article;

import com.exalt.infra.actions.ActionsFinder;
import com.exalt.infra.dataprovider.DataProviderFinder;
import com.exalt.pom.conduitpages.*;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static com.exalt.infra.utils.Constants.EDITOR_PAGE;
import static com.exalt.infra.utils.Constants.HOME_PAGE;

@Test(dataProvider = "Excel", dataProviderClass = DataProviderFinder.class)
public class ArticleTest {
    private WebDriver webDriver;
    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    private ConduitLoginPage conduitLoginPage;
    private ConduitHomePage conduitHomePage;
    private ConduitProfilePage conduitProfilePage;
    private ConduitArticlePreviewPage conduitArticlePreviewPage;
    private ConduitNewArticlePage conduitNewArticlePage;
    private WebDriverWait wait;

    private String testingErrorMessage = "email or password is invalid";

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser);
        BrowserFactory.openUrl(WEB_DRIVER_URL);
        wait = new WebDriverWait(webDriver, 5);

        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitProfilePage = new ConduitProfilePage(webDriver);
        conduitArticlePreviewPage = new ConduitArticlePreviewPage(webDriver);
        conduitNewArticlePage = new ConduitNewArticlePage(webDriver);
        conduitLoginPage.logIn();
    }

    @AfterMethod
    void tearDown() {
        webDriver.quit();
    }

    @Test(enabled = false)
    public void deleteArticleTest() throws InterruptedException {
        conduitHomePage.clickUserProfileLink();
        conduitProfilePage.clickArticlePreview();
        conduitArticlePreviewPage.deleteArticle();
        ActionsFinder.waitTitleToBe(HOME_PAGE, wait);
    }

    public void postNewArticleTest(String title, String articleAbout, String body, String endTag) throws InterruptedException {
        conduitHomePage.clickNewArticleLink();
        wait.until(ExpectedConditions.titleIs(EDITOR_PAGE));
        conduitNewArticlePage.postNewArticle(title, articleAbout, body, endTag);
        ActionsFinder.waitTitleToBe(title + " — Conduit", wait);
    }
}
