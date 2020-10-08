package com.exalt.tests.conduittests.article;

import com.exalt.infra.actions.Actionsf;
import com.exalt.infra.dataprovider.ExcelDataProvider;
import org.testng.annotations.*;

import static com.exalt.infra.utils.Constants.*;

@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
public class PostArticleTest extends BaseArticle {

    @AfterMethod
    public void afterMethod() {
        super.afterMethod();
        conduitNewArticlePage.clickSettingsLink();
        conduitSettingsPage.clickHereToLogoutButton();
        Actionsf.waitTitleToBe(HOME_PAGE);
        conduitHomePage.clickSignInLink();
        conduitLoginPage.logIn();
    }

    public void postNewArticleTest(String title, String articleAbout, String body, String endTag) {
        conduitHomePage.clickNewArticleLink();
        Actionsf.waitTitleToBe(EDITOR_PAGE);
        conduitNewArticlePage.postNewArticle(title, articleAbout, body, endTag);
        /*
         test verification, The article must published in both Profile page and global feed page
         */
        Actionsf.waitTitleToBe(title + " — Conduit");
        conduitHomePage.clickProfileLink();
        Actionsf.waitTitleToBe(USER_PAGE);
        Actionsf.assertEquals(Actionsf.getText(conduitProfilePage.articleTitle), title);
        conduitProfilePage.clickHomeLink();
        conduitHomePage.clickGlobalFeedLink();
        Actionsf.assertEquals(Actionsf.getText(conduitGlobalFeedPage.articleTitle), title);
    }
}
