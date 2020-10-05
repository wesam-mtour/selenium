package com.exalt.pom.conduitpages;

import com.exalt.infra.actions.ActionsFinder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.exalt.infra.utils.Constants.EDITOR_PAGE;

public class ConduitNewArticlePage {
    private WebDriver webDriver;
    /*
     top navigation bar
     */
    @FindBy(how = How.LINK_TEXT, using = "Home")
    public WebElement homeLink;

    @FindBy(how = How.LINK_TEXT, using = "Settings")
    public WebElement settingsLink;

    @FindBy(how = How.CSS, using = "a[ui-sref=\"app.profile.main({ username: $ctrl.currentUser.username })\"]")
    public WebElement userProfileLink;
    /*
     end top navigation bar
     */
    @FindBy(how = How.CSS, using = "input[ng-model=\"$ctrl.article.title\"]")
    public WebElement title;

    @FindBy(how = How.CSS, using = "input[ng-model=\"$ctrl.article.description\"]")
    public WebElement articleAbout;

    @FindBy(how = How.CSS, using = "textarea[ng-model=\"$ctrl.article.body\"]")
    public WebElement body;

    @FindBy(how = How.CSS, using = "input[ng-model=\"$ctrl.tagField\"]")
    public WebElement enterTags;

    @FindBy(how = How.CSS, using = "button[type=\"button\"]")
    public WebElement publishArticleButton;

    @FindBy(how = How.CLASS_NAME, using = "error-messages")
    public WebElement listOfErrors;

    public ConduitNewArticlePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        /*
        This initElements method will create all WebElements
         */
        PageFactory.initElements(webDriver, this);
    }

    public void postNewArticle(String title, String articleAbout, String body, String enterTags) throws InterruptedException {
        ActionsFinder.sendKeys(this.title, title);
        ActionsFinder.sendKeys(this.articleAbout, articleAbout);
        ActionsFinder.sendKeys(this.body, body);
        ActionsFinder.sendKeys(this.enterTags, enterTags);
        ActionsFinder.click(publishArticleButton);
    }

    public void clickHomeLink() {
        ActionsFinder.click(this.homeLink);
    }

    public void clickSettingsLink() {
        ActionsFinder.click(this.settingsLink);
    }

    public void clickUserProfileLink() {
        ActionsFinder.click(this.userProfileLink);
    }


}
