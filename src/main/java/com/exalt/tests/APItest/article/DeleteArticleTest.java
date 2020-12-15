package com.exalt.tests.APItest.article;

import com.exalt.infra.APItest.article.ArticleInfra;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

@Test
public class DeleteArticleTest extends BaseArticle {


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
    public void afterMethod() {
    }

    public void deleteArticleTest() throws IOException {
        response = ArticleInfra.deleteArticle(this.slug);
        ArticleInfra.verifyDeleteArticleResponse(response, this.slug);
    }
}
