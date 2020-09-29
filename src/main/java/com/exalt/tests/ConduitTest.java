package com.exalt.tests;

import com.exalt.dataproviderinfra.DataProviderFinder;
import com.exalt.pom.ConduitGlobalFeedPage;
import com.exalt.pom.ConduitNewArticlePage;
import com.exalt.webdriverinitializer.BrowserFactory;
import com.exalt.pom.ConduitLoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.*;

public class ConduitTest {
    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    private WebDriver webDriver;
    private ConduitLoginPage conduitLoginPage;
    private ConduitNewArticlePage conduitNewArticlePage;
    private ConduitGlobalFeedPage conduitGlobalFeedPage;
    private String testingEmail = "wiasm.mtour@gmail.com";
    private String testingPassword = "123456789";


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

    @Test(dataProvider = "Excel", dataProviderClass = DataProviderFinder.class)
    public void ConduitLoginTest(String run, String email, String password) throws InterruptedException {
        if (run.equals("no")) {
            throw new SkipException("Skipping tests because resource was not available.");
        } else {
            conduitLoginPage = new ConduitLoginPage(webDriver);
            conduitLoginPage.loginWithEmailAndPassword(email, password);
        }
    }

    @Test(dataProvider = "Excel", dataProviderClass = DataProviderFinder.class)
    void ConduitPostNewArticleTest(String run, String title, String articleAbout, String body, String endTag) throws InterruptedException {
        if (run.equals("no")) {
            throw new SkipException("Skipping tests because resource was not available.");
        } else {
            conduitLoginPage = new ConduitLoginPage(webDriver);
            conduitLoginPage.loginWithEmailAndPassword(testingEmail, testingPassword);
            conduitNewArticlePage = new ConduitNewArticlePage(webDriver);
            conduitNewArticlePage.postNewArticle(title, articleAbout, body, endTag);
        }
    }

    @Test
    void ConduitLikeTest() throws InterruptedException {
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitLoginPage.loginWithEmailAndPassword(testingEmail, testingPassword);
        conduitGlobalFeedPage = new ConduitGlobalFeedPage(webDriver);
        conduitGlobalFeedPage.applyLike();
    }
}


