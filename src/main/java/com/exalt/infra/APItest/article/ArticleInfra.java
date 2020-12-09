package com.exalt.infra.APItest.article;

import com.exalt.infra.APItest.RestAPIs;
import com.exalt.infra.APItest.login.LoginInfra;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.IOException;

public class ArticleInfra {
    protected static final String POST_ARTICLE_URL = "https://conduit.productionready.io/api/articles";

    @NotNull
    public static JSONObject postArticle(String email, String password, String title, String articleAbout, String body, String endTag) throws IOException {
        JSONObject requestObject = loadToJSON(title, articleAbout, body, endTag);
        String token = LoginInfra.getToken(email, password);
        token = "Token " + token;
        JSONObject response = RestAPIs.POST(POST_ARTICLE_URL, requestObject, token);
        return response;
    }

    @NotNull
    private static JSONObject loadToJSON(String title, String articleAbout, String body, String endTag) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("article", add(title, articleAbout, body, endTag));
        return jsonObject;
    }

    @NotNull
    private static JSONObject add(String str1, String str2, String str3, String str4) {
        JSONObject childData = new JSONObject();
        childData.put("title", str1);
        childData.put("description", str2);
        childData.put("body", str3);
        JSONArray endTag = new JSONArray();
        childData.put("tagList", endTag);
        return childData;
    }

    public static void verifyPostNewArticleResponse(@NotNull JSONObject response, String title, String articleAbout, String body, String endTag) {
        Assert.assertEquals(response.getJSONObject("article").getString("title"), title);
        Assert.assertEquals(response.getJSONObject("article").getString("description"), articleAbout);
        Assert.assertEquals(response.getJSONObject("article").getString("body"), body);
    }
}
