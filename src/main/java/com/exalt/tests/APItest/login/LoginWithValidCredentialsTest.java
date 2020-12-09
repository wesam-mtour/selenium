package com.exalt.tests.APItest.login;

import com.exalt.infra.APItest.login.LoginInfra;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;


public class LoginWithValidCredentialsTest extends BaseLogin {


    @BeforeMethod
    protected void beforeMethod() {
        super.beforeMethod();
    }

    @AfterMethod
    protected void afterMethod() {
        super.afterMethod();
    }

    public void loginWithValidCredentialsTest(String email, String password, String image) throws IOException {
        response = LoginInfra.login(email, password);
        /*
         test verification
         */
        LoginInfra.verifyLoginWithValidCredentialsResponse(response, email, image);
    }
}
