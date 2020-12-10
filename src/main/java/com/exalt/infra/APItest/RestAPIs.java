package com.exalt.infra.APItest;

import java.io.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.testng.Assert;

public class RestAPIs {


    public String GetResponse(String url) throws ClientProtocolException, IOException {
        /*
            The general process for using HttpClient consists of a number of steps:

            Create an instance of HttpClient.
            Create an instance of one of the methods (GetMethod in this case).
                The URL to connect to is passed in to the the method constructor.
            Tell HttpClient to execute the method.
            Read the response.
            Release the connection.
            Deal with the response.
            */
        StringBuffer result = new StringBuffer();
        CloseableHttpClient client = HttpClients.createDefault();
        /*
        Binary data - The actual file retrieved using the URL.
         */
        try {
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = client.execute(request);
            int responseCode = response.getStatusLine().getStatusCode();
            System.out.println("Response Code: " + responseCode);
            try {
                if (responseCode == 200) {
                    System.out.println("Get Response is Successful");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            /*
              block of code to be executed if the responseCode == 200
            */
                }
                return result.toString();

            } catch (Exception ex) {
                result.append("Get Response Failed");
                return result.toString();
            } finally {
                response.close();
            }
        } finally {
            client.close();
        }
    }

    public static JSONObject POST(String url, JSONObject jsonRequestObject) throws IOException {
       return POST(url, jsonRequestObject,"");
    }

    @NotNull
    public static JSONObject POST(String url, @NotNull JSONObject jsonRequestObject, String token) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Content-Type", "application/json; utf-8");
        httppost.addHeader("Accept", "application/json");
        httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
        httppost.addHeader("authorization", token);
        StringEntity params = new StringEntity(jsonRequestObject.toString());
        httppost.setEntity((params));
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        StringBuffer result = new StringBuffer();
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        if (entity != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }
        JSONObject jsonResponseObject = new JSONObject(result.toString());
        httppost.releaseConnection();
        return jsonResponseObject;
    }

    @NotNull
    public static HttpResponse invalidPOST(String url, @NotNull JSONObject jsonRequestObject, String token) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Content-Type", "application/json; utf-8");
        httppost.addHeader("Accept", "application/json");
        httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
        httppost.addHeader("authorization", token);
        StringEntity params = new StringEntity(jsonRequestObject.toString());
        httppost.setEntity((params));
        HttpResponse response = httpclient.execute(httppost);
        return response;
    }
}