package com.exalt.tests.APItest.article;

import com.exalt.infra.APItest.article.ArticleInfra;
import com.exalt.infra.dataprovider.ExcelDataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;


@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
public class PostArticleTest extends BaseArticle {

    @AfterMethod
    public void afterMethod() {

    }

    public void postNewArticleTest(String email, String password, String title,
                                   String articleAbout, String body, String endTag) throws IOException {
        response = ArticleInfra.postArticle(email, password, title, articleAbout, body, endTag);
        ArticleInfra.verifyPostNewArticleResponse(response, title, articleAbout, body, endTag);
    }
}
