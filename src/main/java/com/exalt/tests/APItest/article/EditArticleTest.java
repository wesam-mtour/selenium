package com.exalt.tests.APItest.article;

import com.exalt.infra.APItest.article.ArticleInfra;
import com.exalt.infra.dataprovider.ExcelDataProvider;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
public class EditArticleTest extends BaseArticle {


    private JSONObject response;
    private final String EMAIL = "wiasm.mtour@gmail.com";
    private final String PASSWORD = "123456789";
    private String slug;

    @BeforeMethod
    public void beforeMethod() throws IOException {
        super.beforeMethod();
        JSONObject jsonObject = ArticleInfra.postArticle(EMAIL, PASSWORD, "demo title", "demo description", "demo body", "demo tags");
        this.slug = ArticleInfra.getSlug(jsonObject);
    }

    @AfterMethod
    public void afterMethod() throws IOException {
        super.afterMethod();
        ArticleInfra.deleteArticle(this.slug);
    }

    public void validEditArticleTest(String TestCaseNumber, String testDescription,
                                 String title, String description, String body, String endTag) throws IOException {
        response = ArticleInfra.editArticle(this.slug, title, description, body, endTag);
        ArticleInfra.verifyArticleResponse(response, title, description, body, endTag);
    }


}
