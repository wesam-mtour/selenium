package com.exalt.tests.conduittests.settings;

import com.exalt.infra.actions.Actionsf;

import org.testng.annotations.*;

import static com.exalt.infra.utils.Constants.*;

public class ChangePasswordTest extends BaseSettings {


    public void ch_OldPassToInvalidPasswordTest(String email, String newPassword, String errorMessage) throws InterruptedException {
        conduitSettingsPage.changePassword(newPassword);
        Actionsf.assertTrue(Actionsf.isDisplayed(conduitSettingsPage.errorMessage));
        Actionsf.assertTrue(webDriver.getTitle().equals(SETTINGS_PAGE));
        Actionsf.assertEquals(Actionsf.getText(conduitSettingsPage.errorMessage), errorMessage);
        /*
         test verification
         */
        /*
        there is a solution for before and after class
         */
        conduitSettingsPage.clickHereToLogoutButton();
        Actionsf.waitTitleToBe(HOME_PAGE, wait);
        conduitHomePage.clickSignInLink();
        Actionsf.waitTitleToBe(SIGN_IN_PAGE, wait);
        conduitLoginPage.logIn(email, newPassword);
        Actionsf.assertTrue(Actionsf.isDisplayed(conduitLoginPage.errorMessage));
        Actionsf.assertTrue(webDriver.getTitle().equals(SIGN_IN_PAGE));
        Actionsf.clear(conduitLoginPage.email);
        Actionsf.clear(conduitLoginPage.password);
        /*
        To be able to login again with the old password in the event of an error in one of the previous tests
         */
        conduitLoginPage.logIn();
        Actionsf.waitTitleToBe(HOME_PAGE, wait);


    }

    @Test(enabled = false)

    public void ch_OldPassToValidPasswordTest(String email, String newPassword) {

        conduitSettingsPage.changePassword(newPassword);
        Actionsf.waitTitleToBe(USER_PAGE, wait);
        /*
         test verification
         */
        conduitProfilePage.clickSettingsLink();
        conduitSettingsPage.clickHereToLogoutButton();
        conduitHomePage.clickSignInLink();
        conduitLoginPage.logIn(email, newPassword);
        Actionsf.waitTitleToBe(HOME_PAGE, wait);
        /*
        To be able to log in again with the old password in the event of an error in one of the previous tests
         */
        conduitHomePage.clickSettingsLink();
        conduitSettingsPage.changePassword("123456789");
        conduitSettingsPage.clickHereToLogoutButton();
        Actionsf.waitTitleToBe(HOME_PAGE, wait);
    }


}
