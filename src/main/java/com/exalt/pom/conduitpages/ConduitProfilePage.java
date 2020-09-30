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
    WebElement homeLink;

    @FindBy(how = How.LINK_TEXT, using = "New Article")
    WebElement newArticleLink;

    @FindBy(how = How.LINK_TEXT, using = "Settings")
    WebElement settingsLink;
    /*
     end top navigation bar
     */
    @FindBy(how = How.LINK_TEXT, using = "Edit Profile Settings")
    WebElement editProfileSettingsLink;

    @FindBy(how = How.CSS, using = "a[ui-sref=\"app.article({ slug: $ctrl.article.slug })\"]")
    WebElement articlePreview;

    public ConduitProfilePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        /*
        This initElements method will create all WebElements
         */
        PageFactory.initElements(webDriver, this);
    }

    public void getArticlePreview() throws InterruptedException {
        ActionsFinder.click(articlePreview);
    }
}
