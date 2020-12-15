package com.exalt.tests.APItest.article;

import com.exalt.infra.APItest.article.ArticleInfra;
import com.exalt.infra.dataprovider.ExcelDataProvider;
import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
public class PostArticleTest extends BaseArticle {
    private JSONObject response;
    private HttpResponse httpResponse;
    private String slug;


    @BeforeMethod
    public void beforeMethod() throws IOException {
        super.beforeMethod();

    }
    @AfterMethod
    public void afterMethod() throws IOException {
        super.afterMethod();
        ArticleInfra.deleteArticle(this.slug);
    }

    public void postValidArticleTest(String TestCaseNumber, String testDescription,
                                     String email, String password, String title,
                                     String description, String body, String endTag) throws IOException {
        response = ArticleInfra.postArticle(email, password, title, description, body, endTag);
        this.slug = ArticleInfra.getSlug(response);
        /*
         test verification
         */
        ArticleInfra.verifyArticleResponse(response, title, description, body, endTag);
    }
    public void postInvalidArticleTest(String TestCaseNumber, String testDescription,
                                       String email, String password, String title,
                                       String description, String body, String endTag,
                                       String expectedResponse,
                                       String expectedStatusCode) throws IOException {
        httpResponse = ArticleInfra.postInvalidArticle(email, password, title, description, body, endTag);
        /*
         test verification
         */
        ArticleInfra.verifyStatusCode(httpResponse, expectedStatusCode);
        JSONObject responseMessage = ArticleInfra.getResponseMessage(httpResponse);
        ArticleInfra.verifyInvalidArticleResponse(responseMessage, expectedResponse);
    }
}
