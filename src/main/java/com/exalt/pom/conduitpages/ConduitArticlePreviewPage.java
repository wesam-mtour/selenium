package com.exalt.pom.conduitpages;

import com.exalt.infra.actions.ActionsFinder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.exalt.infra.utils.Constants.HOME_PAGE;

public class ConduitArticlePreviewPage {
    private WebDriver webDriver;
    /*
     top navigation bar
     */
    @FindBy(how = How.LINK_TEXT, using = "Home")
    WebElement homeLink;

    @FindBy(how = How.LINK_TEXT, using = "New Article")
    WebElement newArticleLink;

    @FindBy(how = How.LINK_TEXT, using = "Settings")
    WebElement settingsLink;

    @FindBy(how = How.CSS, using = "a[ui-sref=\"app.profile.main({ username: $ctrl.currentUser.username })\"]")
    WebElement userProfileLink;
    /*
     end top navigation bar
     */
    @FindBy(how = How.LINK_TEXT, using = "Edit Article")
    WebElement  editArticleLink;

    @FindBy(how = How.CSS, using = "button[class=\"btn btn-outline-danger btn-sm\"]")
    WebElement deleteArticleButton;


    public ConduitArticlePreviewPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        /*
        This initElements method will create all WebElements
         */
        PageFactory.initElements(webDriver, this);
    }
    public void deleteArticle() throws InterruptedException {
        ActionsFinder.click(deleteArticleButton);
        WebDriverWait wait = new WebDriverWait(webDriver,5);
        ActionsFinder.waitTitleToBe(HOME_PAGE,wait);
    }

}
