package com.exalt.pom.conduitpages;

import com.exalt.infra.actions.ActionsFinder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ConduitHomePage {
    private WebDriver webDriver;
    /*
     top navigation bar
     */
    @FindBy(how = How.LINK_TEXT, using = "New Article")
    public WebElement newArticleLink;

    @FindBy(how = How.LINK_TEXT, using = "Settings")
    public WebElement settingsLink;

    @FindBy(how = How.CSS, using = "a[ui-sref=\"app.profile.main({ username: $ctrl.currentUser.username })\"]")
    public WebElement userProfileLink;

    @FindBy(how = How.LINK_TEXT, using = "Sign in")
    public WebElement signInLink;
    /*
     end top navigation bar
     */
    @FindBy(how = How.LINK_TEXT, using = "Global Feed")
    public WebElement globalFeedLink;

    public ConduitHomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        /*
        This initElements method will create all WebElements
         */
        PageFactory.initElements(webDriver, this);
    }

    public void clickNewArticleLink() {
        ActionsFinder.click(newArticleLink);
    }

    public void clickSettingsLink() {
        ActionsFinder.click(settingsLink);
    }


    public void clickUserProfileLink() {
        ActionsFinder.click(userProfileLink);
    }

    public void clickGlobalFeedLink() {
        ActionsFinder.click(globalFeedLink);
    }

    public void clickSignInLink() {
        ActionsFinder.click(signInLink);
    }
}
