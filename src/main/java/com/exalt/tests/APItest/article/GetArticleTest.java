package com.exalt.tests.APItest.article;

import com.exalt.infra.APItest.article.ArticleInfra;
import com.exalt.infra.dataprovider.ExcelDataProvider;
import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.testng.annotations.*;

import java.io.IOException;

public class GetArticleTest extends BaseArticle {

    private JSONObject response;
    private final String EMAIL = "wiasm.mtour@gmail.com";
    private final String PASSWORD = "123456789";
    private final String title = "demo title";
    private final String description = "demo description";
    private final String body = "demo body ";
    private final String tags = "demo tags";

    private String slug;


    @BeforeClass
    protected void setup() throws IOException {
        super.setup();
        JSONObject jsonObject = ArticleInfra.postArticle(this.EMAIL, this.PASSWORD, this.title, this.description, this.body, this.tags);
        this.slug = ArticleInfra.getSlug(jsonObject);
    }

    @BeforeMethod
    public void beforeMethod() throws IOException {
        super.beforeMethod();

    }

    @AfterMethod
    public void afterMethod() throws IOException {
    }

    @AfterClass
    protected void tearDown() throws IOException {
        ArticleInfra.deleteArticle(this.slug);

    }

    @Test
    public void getArticleTest() throws IOException {
        response = ArticleInfra.getArticle(this.slug);
        ArticleInfra.verifyArticleResponse(response, this.title, this.description, this.body, this.tags);
    }

    @Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
    public void invalidGetArticleTest(String TestCaseNumber, String testDescription,
                                      String slug, String expectedError) throws IOException {
        response = ArticleInfra.invalidGetArticle(slug);
        ArticleInfra.verifyInvalidArticleResponse(response, expectedError);
    }
}
