package com.exalt.pom;

import com.exalt.actionsinfra.ActionsFinder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
       // ActionsFinder.waitTitleToBe("Home — Conduit");
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
         wait.until(ExpectedConditions.titleIs("Home — Conduit"));
        String currentUrl = webDriver.getCurrentUrl();
        ActionsFinder.assertEquals(currentUrl, "https://demo.productionready.io/#/");
    }

}
