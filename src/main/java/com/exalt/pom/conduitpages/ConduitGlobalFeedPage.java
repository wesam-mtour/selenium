package com.exalt.pom.conduitpages;

import com.exalt.infra.actions.Actionsf;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ConduitGlobalFeedPage {
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
    /*
     end top navigation bar
     */
    @FindBy(how = How.CSS, using = "favorite-btn[article=\"$ctrl.article\"]")
    public WebElement likeButton;

    @FindBy(how = How.CSS, using = "span[class=\"ng-binding ng-scope\"]")
    public WebElement numberOfLikes;

    @FindBy(how = How.TAG_NAME, using = "favorite-btn")
    public List<WebElement> allLinks;

    public ConduitGlobalFeedPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        /*
        This initElements method will create all WebElements
         */
        PageFactory.initElements(webDriver, this);
    }

    public void clickNewArticleLink() {
        Actionsf.click(newArticleLink);
    }

    public void clickUserProfileLink() {
        Actionsf.click(userProfileLink);
    }

    public void clickLikeButton() {
        Actionsf.click(likeButton);
    }
}
