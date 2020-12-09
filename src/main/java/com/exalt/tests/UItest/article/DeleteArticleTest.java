package com.exalt.tests.UItest.article;

import com.exalt.infra.UItest.actions.Actionsf;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static com.exalt.infra.utils.Constants.*;

public class DeleteArticleTest extends BaseArticle {

    private String title = "this title created for testing";
    private String articleAbout = "this description created for testing";
    private String body = "this body created for testing";
    private String endTag = "this eng Tag created for testing";
    WebDriverWait wait;
    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        wait = new WebDriverWait(webDriver,5);
        conduitHomePage.clickNewArticleLink();
        Actionsf.waitTitleToBe(EDITOR_PAGE,wait);
        conduitNewArticlePage.postNewArticle("title...", "...", "...", "...");
        Actionsf.waitTitleToBe("title..." + " — Conduit",wait);
        conduitArticlePreviewPage.clickNewArticleLink();
        conduitNewArticlePage.postNewArticle(title, articleAbout, body, endTag);
    }
    @AfterMethod
    public void afterMethod() {
        super.afterMethod();
        conduitGlobalFeedPage.clickUserProfileLink();
        conduitProfilePage.clickArticlePreview();
        conduitArticlePreviewPage.clickDeleteArticleButton();
        Actionsf.waitTitleToBe(HOME_PAGE,wait);
    }

    public void deleteArticleTest() {
        conduitArticlePreviewPage.clickDeleteArticleButton();
        Actionsf.waitTitleToBe(HOME_PAGE,wait);
        /*
         test verification, The article must published in both Profile page and global feed page
         */
        conduitHomePage.clickProfileLink();
        Actionsf.waitTitleToBe(USER_PAGE,wait);
        Actionsf.assertNotEquals(Actionsf.getText(conduitProfilePage.articleTitle), title);
        conduitProfilePage.clickHomeLink();
        conduitHomePage.clickGlobalFeedLink();
        Actionsf.assertNotEquals(Actionsf.getText(conduitProfilePage.articleTitle), title);
    }
}
