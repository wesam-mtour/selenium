package com.exalt.pom.conduitpages;

import com.exalt.infra.actions.ActionsFinder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ConduitProfilePage {
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

    @FindBy(how = How.LINK_TEXT, using = "Settings")
    public WebElement settingsLink;
    /*
     end top navigation bar
     */
    @FindBy(how = How.LINK_TEXT, using = "Edit Profile Settings")
    public WebElement editProfileSettingsButton;

    @FindBy(how = How.CSS, using = "a[ui-sref=\"app.article({ slug: $ctrl.article.slug })\"]")
    public WebElement articlePreview;

    public ConduitProfilePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        /*
        This initElements method will create all WebElements
         */
        PageFactory.initElements(webDriver, this);
    }

    public void clickHomeLink() {
        ActionsFinder.click(this.homeLink);
    }

    public void clickNewArticleLink() {
        ActionsFinder.click(this.newArticleLink);
    }

    public void clickSettingsLink() {
        ActionsFinder.click(this.settingsLink);
    }

    public void clickArticlePreview() {
        ActionsFinder.click(articlePreview);
    }

    public void clickEditProfileSettingsButton() {
        ActionsFinder.click(editProfileSettingsButton);
    }

}
