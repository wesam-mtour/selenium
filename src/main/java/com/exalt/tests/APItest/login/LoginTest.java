package com.exalt.tests.APItest.login;

import com.exalt.infra.APItest.login.LoginInfra;
import com.exalt.tests.APItest.login.BaseLogin;
import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class LoginTest extends BaseLogin {

    private HttpResponse httpResponse;
    private JSONObject response;


    @BeforeMethod
    protected void beforeMethod() {
        super.beforeMethod();
    }

    @AfterMethod
    protected void afterMethod() {
        super.afterMethod();
    }

    public void loginWithValidCredentialsTest(String TestCaseNumber, String testDescription,
                                              String email, String password, String image) throws IOException {
        response = LoginInfra.login(email, password);
        /*
         test verification
         */
        LoginInfra.verifyLoginWithValidCredentialsResponse(response, email, image);
    }

    public void loginWithInvalidCredentialsTest(String TestCaseNumber, String testDescription,
                                                String email, String password, String expectedResponse,
                                                String expectedStatusCode) throws IOException {
        httpResponse = LoginInfra.invalidLogin(email, password);
        /*
         test verification
         */
        LoginInfra.verifyStatusCode(httpResponse, expectedStatusCode);
        JSONObject responseMessage = LoginInfra.getResponseMessage(httpResponse);
        LoginInfra.verifyLoginWithInvalidCredentialsResponse(responseMessage, expectedResponse);
    }
}
