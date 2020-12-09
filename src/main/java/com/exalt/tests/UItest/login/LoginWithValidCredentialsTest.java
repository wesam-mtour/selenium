package com.exalt.tests.UItest.login;

import com.exalt.infra.UItest.actions.Actionsf;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.exalt.infra.utils.Constants.HOME_PAGE;

public class LoginWithValidCredentialsTest extends BaseLogin {

    @BeforeMethod
    protected void beforeMethod() {
        super.beforeMethod();
    }

    @AfterMethod
    protected void afterMethod() {
        super.afterMethod();
    }

    public void loginWithValidCredentialsTest(String email, String password) {
        conduitLoginPage.logIn(email, password);
        /*
         test verification
         */
        Actionsf.waitTitleToBe(HOME_PAGE);
        conduitHomePage.clickSettingsLink();
        conduitSettingsPage.clickHereToLogoutButton();
        conduitHomePage.clickSignInLink();
    }
}
