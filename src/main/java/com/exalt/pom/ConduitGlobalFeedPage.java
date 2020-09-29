package com.exalt.pom;

import com.exalt.actionsinfra.ActionsFinder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ConduitGlobalFeedPage {
    private WebDriver webDriver;
    @FindBy(how = How.XPATH, using = "/html/body/div/div/div/div[2]/div/div[1]/div/ul/li[2]/a")
    WebElement globalFeedLink;

    @FindBy(how = How.XPATH, using = "/html/body/div/div/div/div[2]/div/div[1]/article-list/article-preview[1]/div/article-meta/div/ng-transclude/favorite-btn/button")
    WebElement likeButton;
    @FindBy(how = How.XPATH, using = "/html/body/div/div/div/div[2]/div/div[1]/article-list/article-preview[1]/div/article-meta/div/ng-transclude/favorite-btn/button/ng-transclude/span")
    WebElement numberOfLikes;

    public ConduitGlobalFeedPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        /*
        This initElements method will create all WebElements
         */
        PageFactory.initElements(webDriver, this);
    }

    public void applyLike() throws InterruptedException {
        ActionsFinder.click(globalFeedLink);
      //  Thread.sleep(5000);
        String oldValue = ActionsFinder.getText(numberOfLikes);
        ActionsFinder.click(likeButton);
        String newValue = ActionsFinder.getText(numberOfLikes, String.valueOf(Integer.valueOf(oldValue) + 1));
        ActionsFinder.assertEquals(Integer.valueOf(newValue), (Integer.valueOf(oldValue) + 1));
    }

}
