package com.exalt.tests.APItest.login;

import com.exalt.infra.APItest.login.LoginInfra;
import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class LoginWithInvalidCredentialsTest extends BaseLogin {
    private HttpResponse response;

    @BeforeMethod
    protected void beforeMethod() {
        super.beforeMethod();
    }

    @AfterMethod
    protected void afterMethod() {
        super.afterMethod();
    }

    public void loginWithInvalidCredentialsTest(String TestCaseNumber, String testDescription,
                                                String email, String password, String expectedResponse,
                                                String expectedStatusCode) throws IOException {
        response = LoginInfra.invalidLogin(email, password);
        /*
         test verification
         */
        LoginInfra.verifyStatusCode(response, expectedStatusCode);
        JSONObject responseMessage = LoginInfra.getResponseMessage(response);
        LoginInfra.verifyLoginWithInvalidCredentialsResponse(responseMessage, expectedResponse);
    }
}
