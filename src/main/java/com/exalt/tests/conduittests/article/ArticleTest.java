package com.exalt.tests.conduittests.article;

import com.exalt.pom.conduitpages.ConduitHomePage;
import com.exalt.pom.conduitpages.ConduitLoginPage;
import com.exalt.pom.conduitpages.ConduitProfilePage;
import com.exalt.pom.conduitpages.ConduitUserSettingsPage;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ArticleTest {
    private WebDriver webDriver;
    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    private ConduitLoginPage conduitLoginPage;
    private ConduitHomePage conduitHomePage;
    private ConduitUserSettingsPage conduitUserSettingsPage;
    private ConduitProfilePage conduitProfilePage;
    private String testingErrorMessage = "email or password is invalid";
    private WebDriverWait wait;
    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser);
        BrowserFactory.openUrl(WEB_DRIVER_URL);
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitHomePage = new ConduitHomePage(webDriver);
        conduitUserSettingsPage = new ConduitUserSettingsPage(webDriver);
        conduitProfilePage = new ConduitProfilePage(webDriver);
        wait = new WebDriverWait(webDriver, 5);
        //conduitLoginPage.logIn();
    }
    @AfterMethod void tearDown(){
        webDriver.quit();
    }

    @Test(enabled = true)
    void ConduitDeleteArticleTest() throws InterruptedException {
        conduitHomePage.clickUserProfileLink();
        conduitProfilePage.clickArticlePreview();
    //   conduitArticlePreviewPage.deleteArticle();
    }
}
