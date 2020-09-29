package com.exalt.pom;

import com.exalt.actionsinfra.ActionsFinder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConduitNewArticlePage {
    private WebDriver webDriver;

    @FindBy(how = How.LINK_TEXT, using = "New Article")
    WebElement newArticleButton;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Article Title']")
    WebElement title;
    @FindBy(how = How.XPATH, using = "//input[@placeholder=\"What's this article about?\"]")
    WebElement articleAbout;
    @FindBy(how = How.XPATH, using = "//textarea[@placeholder='Write your article (in markdown)']")
    WebElement body;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Enter tags']")
    WebElement enterTags;
    @FindBy(how = How.CSS, using = "button[type=\"button\"]")
    WebElement publishArticleButton;
    @FindBy(how = How.CLASS_NAME, using = "error-messages")
    WebElement listOfErrors;
    @FindBy(how = How.XPATH, using = "/html/body/div/div/div/div/div/div/list-errors/ul/div[1]")
    WebElement titleErrorMessages;
    @FindBy(how = How.XPATH, using = "/html/body/div/div/div/div/div/div/list-errors/ul/div[2]")
    WebElement bodyErrorMessages;
    @FindBy(how = How.XPATH, using = "/html/body/div/div/div/div/div/div/list-errors/ul/div[3]")
    WebElement articleAboutErrorMessages;


    public ConduitNewArticlePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        /*
        This initElements method will create all WebElements
         */
        PageFactory.initElements(webDriver, this);
    }

    public void postNewArticle(String title, String articleAbout, String body, String enterTags) throws InterruptedException {
        ActionsFinder.click(newArticleButton);
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.titleIs("Editor — Conduit"));
        ActionsFinder.sendKeys(this.title, title);
        ActionsFinder.sendKeys(this.articleAbout, articleAbout);
        ActionsFinder.sendKeys(this.body, body);
        ActionsFinder.sendKeys(this.enterTags, enterTags);
        ActionsFinder.click(publishArticleButton);
        //try {
            wait.until(ExpectedConditions.titleIs(title + " — Conduit"));
        //}
        //catch (Exception e) {

//            if (title.isEmpty()&&body.isEmpty()&&articleAbout.isEmpty()){
//                wait.until(ExpectedConditions.visibilityOf(titleErrorMessages));
//                wait.until(ExpectedConditions.visibilityOf(bodyErrorMessages));
//                wait.until(ExpectedConditions.visibilityOf(articleAboutErrorMessages));
//                wait.until(ExpectedConditions.visibilityOf(listOfErrors));
//
//            }
        //}
    }
}
