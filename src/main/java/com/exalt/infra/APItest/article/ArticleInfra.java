package com.exalt.infra.APItest.article;

import com.exalt.infra.APItest.RestAPIs;
import com.exalt.infra.APItest.login.LoginInfra;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArticleInfra {
    protected static final String POST_ARTICLE_URL = "https://conduit.productionready.io/api/articles";

    @NotNull
    public static JSONObject postArticle(String email, String password, String title, String description, String body, String endTag) throws IOException {
        JSONObject requestObject = loadToJSON(title, description, body, endTag);
        String token = LoginInfra.getToken(email, password);
        token = "Token " + token;
        JSONObject response = RestAPIs.POST(POST_ARTICLE_URL, requestObject, token);
        return response;
    }

    @NotNull
    public static HttpResponse postInvalidArticle(String email, String password, String title, String description, String body, String endTag) throws IOException {
        JSONObject requestObject = loadToJSON(title, description, body, endTag);
        String token = LoginInfra.getToken(email, password);
        token = "Token " + token;
        HttpResponse response = RestAPIs.invalidPOST(POST_ARTICLE_URL, requestObject, token);
        return response;
    }
    public static void verifyStatusCode(HttpResponse httpResponse, String expectedStatusCode) {
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), Integer.parseInt(expectedStatusCode));
    }

    @NotNull
    public static JSONObject getResponseMessage(HttpResponse httpResponse) throws IOException {
        HttpEntity entity = httpResponse.getEntity();
        StringBuffer result = new StringBuffer();
        if (entity != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }
        JSONObject response = new JSONObject(result.toString());
        return response;
    }




    @NotNull
    private static JSONObject loadToJSON(String title, String description, String body, String endTag) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("article", add(title, description, body, endTag));
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

    public static void verifyPostNewArticleResponse(@NotNull JSONObject response, String title, String description, String body, String endTag) {
        Assert.assertEquals(response.getJSONObject("article").getString("title"), title);
        Assert.assertEquals(response.getJSONObject("article").getString("description"), description);
        Assert.assertEquals(response.getJSONObject("article").getString("body"), body);
    }
    public static void verifyPostInvalidArticleResponse(@NotNull JSONObject response, String expectedResponse) {
        Assert.assertEquals(response.toString(), expectedResponse);
    }
}
