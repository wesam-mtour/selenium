package com.exalt.tests.APItest.article;

import com.exalt.infra.APItest.article.ArticleInfra;
import com.exalt.infra.dataprovider.ExcelDataProvider;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;


@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
public class PostArticleTest extends BaseArticle {
    private JSONObject response;

    @BeforeMethod
    public void beforeMethod(){
        super.beforeMethod();

    }
    @AfterMethod
    public void afterMethod() {
        super.afterMethod();

    }

    public void postNewArticleTest(String TestCaseNumber, String testDescription,
                                   String email, String password, String title,
                                   String description, String body, String endTag) throws IOException {
        response = ArticleInfra.postArticle(email, password, title, description, body, endTag);
        ArticleInfra.verifyPostNewArticleResponse(response, title, description, body, endTag);
    }
}
