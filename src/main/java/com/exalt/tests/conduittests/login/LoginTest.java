package com.exalt.tests.conduittests.login;

import com.exalt.infra.actions.Actionsf;
import org.testng.annotations.*;

import static com.exalt.infra.utils.Constants.HOME_PAGE;
import static com.exalt.infra.utils.Constants.SIGN_IN_PAGE;

public class LoginTest extends BaseLogin {

    public void loginWithInvalidCredentialsTest(String email, String password, String expectedErrorMessage) throws InterruptedException {
        conduitLoginPage.logIn(email, password);
        Actionsf.isDisplayed(conduitLoginPage.errorMessage);
        Actionsf.assertEquals(Actionsf.getText(conduitLoginPage.errorMessage), expectedErrorMessage);
        Actionsf.assertEquals(webDriver.getTitle(), SIGN_IN_PAGE);
        Actionsf.clear(conduitLoginPage.email);
        Actionsf.clear(conduitLoginPage.password);
    }

    @Test(enabled = false)
    public void loginWithValidCredentialsTest(String email, String password) {
        conduitLoginPage.logIn(email, password);
        Actionsf.waitTitleToBe(HOME_PAGE);
        conduitHomePage.clickSettingsLink();
        conduitSettingsPage.clickHereToLogoutButton();
        conduitHomePage.clickSignInLink();
    }
}
