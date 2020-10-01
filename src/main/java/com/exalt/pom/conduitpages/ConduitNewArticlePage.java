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
    WebElement homeLink;

    @FindBy(how = How.LINK_TEXT, using = "Settings")
    WebElement settingsLink;

    @FindBy(how = How.CSS, using = "a[ui-sref=\"app.profile.main({ username: $ctrl.currentUser.username })\"]")
    WebElement userProfileLink;
    /*
     end top navigation bar
     */
    @FindBy(how = How.CSS, using = "input[ng-model=\"$ctrl.article.title\"]")
    WebElement title;

    @FindBy(how = How.CSS, using = "input[ng-model=\"$ctrl.article.description\"]")
    WebElement articleAbout;

    @FindBy(how = How.CSS, using = "textarea[ng-model=\"$ctrl.article.body\"]")
    WebElement body;

    @FindBy(how = How.CSS, using = "input[ng-model=\"$ctrl.tagField\"]")
    WebElement enterTags;

    @FindBy(how = How.CSS, using = "button[type=\"button\"]")
    WebElement publishArticleButton;

    @FindBy(how = How.CLASS_NAME, using = "error-messages")
    WebElement listOfErrors;

    public ConduitNewArticlePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        /*
        This initElements method will create all WebElements
         */
        PageFactory.initElements(webDriver, this);
    }

    public void postNewArticle(String title, String articleAbout, String body, String enterTags) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.titleIs(EDITOR_PAGE));
        ActionsFinder.sendKeys(this.title, title);
        ActionsFinder.sendKeys(this.articleAbout, articleAbout);
        ActionsFinder.sendKeys(this.body, body);
        ActionsFinder.sendKeys(this.enterTags, enterTags);
        ActionsFinder.click(publishArticleButton);
        //try {
        wait.until(ExpectedConditions.titleIs(title + " — Conduit"));
        //}
        //catch (Exception e) {
//            if (&&body.isEmpty()&&articleAbout.isEmpty()){
//                wait.until(ExpectedConditions.visibilityOf(titleErrorMessages));
//                wait.until(ExpectedConditions.visibilityOf(bodyErrorMessages));
//                wait.until(ExpectedConditions.visibilityOf(articleAboutErrorMessages));
//                wait.until(ExpectedConditions.visibilityOf(listOfErrors));
//
//            }
        //}
    }

    public void getUserProfileLink() {
        ActionsFinder.click(userProfileLink);
    }
}
