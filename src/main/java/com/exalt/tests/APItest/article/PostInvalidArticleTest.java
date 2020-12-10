package com.exalt.tests.APItest.article;


import com.exalt.infra.APItest.article.ArticleInfra;
import com.exalt.infra.APItest.login.LoginInfra;
import com.exalt.infra.dataprovider.ExcelDataProvider;
import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
public class PostInvalidArticleTest extends BaseArticle {
    private HttpResponse response;

    @BeforeMethod
    public void beforeMethod() {

    }

    @AfterMethod
    public void afterMethod() {

    }

    public void postInvalidArticleTest(String TestCaseNumber, String testDescription,
                                       String email, String password, String title,
                                       String description, String body, String endTag,
                                       String expectedResponse,
                                       String expectedStatusCode) throws IOException {
        response = ArticleInfra.postInvalidArticle(email, password, title, description, body, endTag);
        /*
         test verification
         */
        ArticleInfra.verifyStatusCode(response, expectedStatusCode);
        JSONObject responseMessage = ArticleInfra.getResponseMessage(response);
        ArticleInfra.verifyPostInvalidArticleResponse(responseMessage, expectedResponse);
    }
}
