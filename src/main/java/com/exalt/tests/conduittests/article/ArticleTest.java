package com.exalt.tests.conduittests.article;

import com.exalt.infra.actions.Actionsf;
import org.testng.annotations.*;
import static com.exalt.infra.utils.Constants.*;

@Test
public class ArticleTest extends BaseArticle {

    private String title = "this title created for testing";
    private String articleAbout = "this description created for testing";
    private String body = "this body created for testing";
    private String endTag = "this eng Tag created for testing";

    @BeforeMethod
    public void beforeMethod() {
        conduitHomePage.clickNewArticleLink();
        Actionsf.waitTitleToBe(EDITOR_PAGE);
        conduitNewArticlePage.postNewArticle(title, articleAbout, body, endTag);
    }

    @Test(enabled = true)
    public void deleteArticleTest() {
        conduitArticlePreviewPage.clickDeleteArticleButton();
        Actionsf.waitTitleToBe(HOME_PAGE);
        conduitHomePage.clickProfileLink();
        Actionsf.waitTitleToBe(USER_PAGE);
        Actionsf.assertNotEquals(Actionsf.getText(conduitProfilePage.articleTitle), title);
        conduitProfilePage.clickHomeLink();
        conduitHomePage.clickGlobalFeedLink();
        Actionsf.assertNotEquals(Actionsf.getText(conduitProfilePage.articleTitle), title);
    }

}
