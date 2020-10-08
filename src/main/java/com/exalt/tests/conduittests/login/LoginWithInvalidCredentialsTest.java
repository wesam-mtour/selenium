package com.exalt.tests.conduittests.login;

import com.exalt.infra.actions.Actionsf;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.exalt.infra.utils.Constants.HOME_PAGE;
import static com.exalt.infra.utils.Constants.SIGN_IN_PAGE;

public class LoginWithInvalidCredentialsTest extends BaseLogin {

    @BeforeMethod
    protected void beforeMethod() {
        super.beforeMethod();
    }

    @AfterMethod
    protected void afterMethod() {
        super.afterMethod();
        conduitHomePage.clickSettingsLink();
        conduitSettingsPage.clickHereToLogoutButton();
        Actionsf.waitTitleToBe(HOME_PAGE);
        conduitHomePage.clickSignInLink();
    }

    public void loginWithInvalidCredentialsTest(String email, String password, String expectedErrorMessage) throws InterruptedException {
        conduitLoginPage.logIn(email, password);
        /*
         test verification
         */
        Actionsf.isDisplayed(conduitLoginPage.errorMessage);
        Actionsf.assertEquals(Actionsf.getText(conduitLoginPage.errorMessage), expectedErrorMessage);
        Actionsf.assertEquals(webDriver.getTitle(), SIGN_IN_PAGE);
        conduitLoginPage.logIn();
        Actionsf.waitTitleToBe(HOME_PAGE);
    }

}