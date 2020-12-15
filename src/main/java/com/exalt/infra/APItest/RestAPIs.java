package com.exalt.infra.APItest;

import java.io.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.testng.Assert;

public class RestAPIs {


    @NotNull
    public static JSONObject get(String url) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        setHeaders(httpGet, "");
        HttpResponse response = httpClient.execute(httpGet);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
        JSONObject jsonResponseObject = getResponseEntity(response);
        return jsonResponseObject;
    }

    @NotNull
    public static JSONObject invalidGet(String url) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        setHeaders(httpGet, "");
        HttpResponse response = httpClient.execute(httpGet);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),404);
        JSONObject jsonResponseObject = getResponseEntity(response);
        return jsonResponseObject;
    }

    @NotNull
    public static JSONObject delete(String url, String token) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);
        setHeaders(httpDelete, token);
        HttpResponse response = httpClient.execute(httpDelete);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
        JSONObject jsonResponseObject = getResponseEntity(response);
        return jsonResponseObject;
    }

    @NotNull
    public static JSONObject put(String url, @NotNull JSONObject jsonRequestObject, String token) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        setHeaders(httpPut, token);
        StringEntity params = new StringEntity(jsonRequestObject.toString());
        httpPut.setEntity(params);
        HttpResponse response = httpClient.execute(httpPut);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
        JSONObject jsonResponseObject = getResponseEntity(response);
        return jsonResponseObject;
    }

    @NotNull
    public static JSONObject post(String url, JSONObject jsonRequestObject) throws IOException {
        return post(url, jsonRequestObject, "");
    }

    @NotNull
    public static JSONObject post(String url, @NotNull JSONObject jsonRequestObject, String token) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        setHeaders(httpPost, token);
        StringEntity params = new StringEntity(jsonRequestObject.toString());
        httpPost.setEntity(params);
        HttpResponse response = httpClient.execute(httpPost);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
        JSONObject jsonResponseObject = getResponseEntity(response);
        return jsonResponseObject;
    }

    @NotNull
    public static HttpResponse invalidPost(String url, @NotNull JSONObject jsonRequestObject, String token) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        setHeaders(httpPost, token);
        StringEntity params = new StringEntity(jsonRequestObject.toString());
        httpPost.setEntity(params);
        HttpResponse response = httpClient.execute(httpPost);
        return response;
    }

    @NotNull
    private static JSONObject getResponseEntity(@NotNull HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        StringBuffer result = new StringBuffer();
        if (entity != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }
        JSONObject jsonResponseObject = new JSONObject(result.toString());
        return jsonResponseObject;
    }

    private static void setHeaders(@NotNull HttpRequestBase httpMethod, String token) {
        httpMethod.addHeader("Content-Type", "application/json; utf-8");
        httpMethod.addHeader("Accept", "application/json");
        httpMethod.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
        httpMethod.addHeader("authorization", token);
    }
}