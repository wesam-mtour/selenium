package com.exalt.infra.APItest.login;

import com.exalt.infra.APItest.RestAPIs;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.IOException;

public class LoginInfra {
    private static final String LOGIN_URL = "https://conduit.productionready.io/api/users/login";

    @NotNull
    public static JSONObject login(String email, String password) throws IOException {
        JSONObject jsonObject = loadToJSON(email, password);
        String token = "";
        JSONObject response = RestAPIs.POST(LOGIN_URL, jsonObject, token);
        return response;
    }

    @NotNull
    public static JSONObject invalidLogin(String email, String password) throws IOException {
        JSONObject jsonObject = loadToJSON(email, password);
        String token = "";
        JSONObject response = RestAPIs.invalidPOST(LOGIN_URL, jsonObject, token);
        return response;
    }


    @NotNull
    public static String getToken(String email, String password) throws IOException {
        JSONObject jsonObject = loadToJSON(email, password);
        String token = "";
        JSONObject response = RestAPIs.POST(LOGIN_URL, jsonObject, token);
        String generatedToken = response.getJSONObject("user").getString("token");
        return generatedToken;
    }


    @NotNull
    private static JSONObject loadToJSON(String email, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", add(email, password));
        return jsonObject;
    }

    @NotNull
    private static JSONObject add(String str1, String str2) {
        JSONObject childData = new JSONObject();
        childData.put("email", str1);
        childData.put("password", str2);
        return childData;
    }

    public static void verifyLoginWithInvalidCredentialsResponse(@NotNull JSONObject response, String expectedResponse) {
        Assert.assertEquals(response.toString(), expectedResponse);
    }

    public static void verifyLoginWithValidCredentialsResponse(@NotNull JSONObject response, String email, String image) {
        Assert.assertEquals(response.getJSONObject("user").getString("email"), email);
        Assert.assertEquals(response.getJSONObject("user").getString("image"), image);
    }
}
