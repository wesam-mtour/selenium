package com.exalt.pom.conduitpages;

import com.exalt.infra.actions.ActionsFinder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ConduitGlobalFeedPage {
    private WebDriver webDriver;
    /*
     top navigation bar
     */
    @FindBy(how = How.LINK_TEXT, using = "New Article")
    WebElement newArticleLink;

    @FindBy(how = How.LINK_TEXT, using = "Settings")
    WebElement settingsLink;

    @FindBy(how = How.CSS, using = "a[ui-sref=\"app.profile.main({ username: $ctrl.currentUser.username })\"]")
    WebElement userProfileLink;
    /*
     end top navigation bar
     */
    @FindBy(how = How.CSS, using = "favorite-btn[article=\"$ctrl.article\"]")
    WebElement likeButton;

    @FindBy(how = How.CSS, using = "span[class=\"ng-binding ng-scope\"]")
    WebElement numberOfLikes;

    public ConduitGlobalFeedPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        /*
        This initElements method will create all WebElements
         */
        PageFactory.initElements(webDriver, this);
    }

    public void applyLike()  {
        String oldValue = ActionsFinder.getText(numberOfLikes);
        ActionsFinder.click(likeButton);
        String newValue = ActionsFinder.getText(numberOfLikes, String.valueOf(Integer.valueOf(oldValue) + 1));
        ActionsFinder.assertEquals(Integer.valueOf(newValue), (Integer.valueOf(oldValue) + 1));
    }

    public void applyDisLike()  {
        String oldValue = ActionsFinder.getText(numberOfLikes);
        ActionsFinder.click(likeButton);
        String newValue = ActionsFinder.getText(numberOfLikes, String.valueOf(Integer.valueOf(oldValue) - 1));
        ActionsFinder.assertEquals(Integer.valueOf(newValue), (Integer.valueOf(oldValue) - 1));
    }
}
