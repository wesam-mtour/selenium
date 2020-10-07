package com.exalt.tests.conduittests.article;

import com.exalt.infra.actions.Actionsf;
import com.exalt.infra.dataprovider.ExcelDataProvider;
import org.testng.annotations.*;

import static com.exalt.infra.utils.Constants.EDITOR_PAGE;
import static com.exalt.infra.utils.Constants.USER_PAGE;

@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
public class PostArticleTest extends BaseArticle {

    public void postNewArticleTest(String title, String articleAbout, String body, String endTag) throws InterruptedException {
        conduitHomePage.clickNewArticleLink();
        Actionsf.waitTitleToBe(EDITOR_PAGE);
        conduitNewArticlePage.postNewArticle(title, articleAbout, body, endTag);
        Actionsf.waitTitleToBe(title + " — Conduit");
        conduitHomePage.clickProfileLink();
        Actionsf.waitTitleToBe(USER_PAGE);
        Actionsf.assertEquals(Actionsf.getText(conduitProfilePage.articleTitle), title);
        conduitProfilePage.clickHomeLink();
        conduitHomePage.clickGlobalFeedLink();
        Actionsf.assertEquals(Actionsf.getText(conduitProfilePage.articleTitle), title);
    }
}
