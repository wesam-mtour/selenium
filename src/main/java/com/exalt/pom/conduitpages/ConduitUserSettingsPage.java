package com.exalt.pom.conduitpages;

import com.exalt.infra.actions.ActionsFinder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.exalt.infra.utils.Constants.*;

public class ConduitUserSettingsPage {
    private WebDriver webDriver;
    /*
    Used to mark a field on a Page Object to indicate an alternative mechanism for locating the element or
     a list of elements. Used in conjunction with PageFactory this allows users to quickly and easily create PageObjects
     */
    /*
     top navigation bar
     */
    @FindBy(how = How.LINK_TEXT, using = "Home")
    WebElement homeLink;

    @FindBy(how = How.LINK_TEXT, using = "New Article")
    WebElement newArticleLink;

    @FindBy(how = How.CSS, using = "a[ui-sref=\"app.profile.main({ username: $ctrl.currentUser.username })\"]")
    WebElement userProfileLink;
    /*
     end top navigation bar
     */
    @FindBy(how = How.CSS, using = "li[ng-repeat=\"error in errors\"]")
    WebElement errorMessage;
    @FindBy(how = How.CSS, using = "input[placeholder=\"URL of profile picture\"]")
    WebElement urlProfilePicture;
    @FindBy(how = How.CSS, using = "input[placeholder=\"Username\"]")
    WebElement userName;
    @FindBy(how = How.CSS, using = "textarea[placeholder=\"Short bio about you\"]")
    WebElement bio;
    @FindBy(how = How.CSS, using = "input[type=\"email\"]")
    WebElement email;
    @FindBy(how = How.CSS, using = "input[type=\"password\"]")
    WebElement password;
    @FindBy(how = How.CSS, using = "button[type=\"submit\"]")
    WebElement updateSettingsButton;

    @FindBy(how = How.CSS, using = "button[class=\"btn btn-outline-danger\"]")
    WebElement orClickHereToLogoutButton;

    public ConduitUserSettingsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        /*
        This initElements method will create all WebElements
         */
        PageFactory.initElements(webDriver, this);
    }

    public void setNewPassword(String newPassword, String expectedErrorMessage) {
        ActionsFinder.sendKeys(this.password, newPassword);
        ActionsFinder.click(updateSettingsButton);
        try {
            ActionsFinder.isDisplayed(this.errorMessage);
            webDriver.getTitle().equals(SETTINGS_PAGE);
            ActionsFinder.assertEquals(this.errorMessage.getText(), expectedErrorMessage);
        } catch (Exception e) {
            ActionsFinder.assertEquals(webDriver.getTitle(), "@" + ActionsFinder.getText(userProfileLink) + " — Conduit");
        }
    }

    public void getOrClickHereToLogoutButton() {
        ActionsFinder.click(orClickHereToLogoutButton);
    }


}
