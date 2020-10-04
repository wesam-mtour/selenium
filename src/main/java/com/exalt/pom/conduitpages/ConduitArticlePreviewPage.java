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
    WebElement editArticleButton;

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

    public WebElement getHomeLink() {
        return this.homeLink;
    }

    public void clickHomeLink() {
ActionsFinder.click(this.homeLink);
    }

    public WebElement getNewArticleLink() {
        return this.newArticleLink;
    }

    public void clickNewArticleLink() {
        ActionsFinder.click(this.newArticleLink);
    }

    public WebElement getSettingsLink() {
        return this.settingsLink;
    }

    public void clickSettingsLink() {
        ActionsFinder.click(this.settingsLink);
    }

    public WebElement getUserProfileLink() {
        return this.userProfileLink;
    }

    public void clickUserProfileLink() {
        ActionsFinder.click(this.userProfileLink);
    }

    public WebElement getEditArticleButton() {
        return this.editArticleButton;
    }

    public void clickEditArticleButton() {
        ActionsFinder.click(this.editArticleButton);
    }

    public WebElement getDeleteArticleButton() {
        return this.deleteArticleButton;
    }

    public void clickDeleteArticleButton() {
        ActionsFinder.click(this.deleteArticleButton);
    }
}
