package com.exalt.pom.conduitpages;

import com.exalt.infra.actions.Actionsf;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ConduitSettingsPage {
    private WebDriver webDriver;

    /*
        Used to mark a field on a Page Object to indicate an alternative mechanism for locating the element or
         a list of elements. Used in conjunction with PageFactory this allows users to quickly and easily create PageObjects
         */
    /*
     top navigation bar
     */
    @FindBy(how = How.LINK_TEXT, using = "Home")
    public WebElement homeLink;

    @FindBy(how = How.LINK_TEXT, using = "New Article")
    public WebElement newArticleLink;

    @FindBy(how = How.CSS, using = "a[ui-sref=\"app.profile.main({ username: $ctrl.currentUser.username })\"]")
    public WebElement profileLink;
    /*
     end top navigation bar
     */
    @FindBy(how = How.CSS, using = "li[ng-repeat=\"error in errors\"]")
    public WebElement errorMessage;
    @FindBy(how = How.CSS, using = "input[placeholder=\"URL of profile picture\"]")
    public WebElement urlProfilePicture;
    @FindBy(how = How.CSS, using = "input[placeholder=\"Username\"]")
    public WebElement userName;
    @FindBy(how = How.CSS, using = "textarea[placeholder=\"Short bio about you\"]")
    public WebElement bio;
    @FindBy(how = How.CSS, using = "input[type=\"email\"]")
    public WebElement email;
    @FindBy(how = How.CSS, using = "input[type=\"password\"]")
    public WebElement password;
    @FindBy(how = How.CSS, using = "button[type=\"submit\"]")
    public WebElement updateSettingsButton;
    @FindBy(how = How.CSS, using = "button[class=\"btn btn-outline-danger\"]")
    public WebElement orClickHereToLogoutButton;


    public ConduitSettingsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        /*
        This initElements method will create all WebElements
         */
        PageFactory.initElements(webDriver, this);
    }

    public void changePassword(String newPassword) {
        Actionsf.sendKeys(this.password, newPassword);
        Actionsf.click(updateSettingsButton);
    }


    public void clickHomeLink() {
        Actionsf.click(this.homeLink);
    }

    public void clickNewArticleLink() {
        Actionsf.click(this.newArticleLink);
    }

    public void clickProfileLink() {
        Actionsf.click(this.profileLink);
    }

    public void clickUpdateSettingsButton() {
        Actionsf.click(this.updateSettingsButton);
    }

    public void clickHereToLogoutButton() {
        Actionsf.click(this.orClickHereToLogoutButton);
    }


}
