package com.exalt.pom.conduitpages;

import com.exalt.infra.actions.ActionsFinder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.exalt.infra.utils.Constants.HOME_PAGE;

public class ConduitLoginPage {
    private WebDriver webDriver;
    /*
    Used to mark a field on a Page Object to indicate an alternative mechanism for locating the element or
     a list of elements. Used in conjunction with PageFactory this allows users to quickly and easily create PageObjects
     */
    @FindBy(how = How.CSS, using = "input[type=\"email\"]")
    WebElement email;

    @FindBy(how = How.CSS, using = "input[type=\"password\"]")
    WebElement password;

    @FindBy(how = How.CSS, using = "button[type=\"submit\"]")
    WebElement signInButton;

    public ConduitLoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        /*
        This initElements method will create all WebElements
         */
        PageFactory.initElements(webDriver, this);
    }

    public void loginWithEmailAndPassword(String email, String password) throws InterruptedException {
        ActionsFinder.sendKeys(this.email, email);
        ActionsFinder.sendKeys(this.password, password);
        ActionsFinder.click(signInButton);
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        ActionsFinder.waitTitleToBe(HOME_PAGE,wait);
    }

}
