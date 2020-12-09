package com.exalt.tests.APItest.login;

import com.exalt.infra.APItest.login.LoginInfra;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class LoginWithInvalidCredentialsTest extends BaseLogin {

    @BeforeMethod
    protected void beforeMethod() {
        super.beforeMethod();
    }

    @AfterMethod
    protected void afterMethod() {
        super.afterMethod();
    }

    public void loginWithInvalidCredentialsTest(String email, String password, String expectedResponse) throws IOException {
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 422);
        response = LoginInfra.invalidLogin(email, password);
        /*
         test verification
         */
        LoginInfra.verifyLoginWithInvalidCredentialsResponse(response, expectedResponse);

    }

}
