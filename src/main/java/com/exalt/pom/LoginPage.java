package com.exalt.pom;

import com.exalt.actionsinfra.ActionsFinder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver webDriver;
    /*
    Used to mark a field on a Page Object to indicate an alternative mechanism for locating the element or
     a list of elements. Used in conjunction with PageFactory this allows users to quickly and easily create PageObjects
     */
    @FindBy(how = How.ID, using = "demo-email")
    WebElement userName;

    @FindBy(how = How.ID, using = "demo-pwd")
    WebElement userPassword;

    @FindBy(how = How.CSS, using = ".btn")
    WebElement submitButton;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        //This initElements method will create all WebElements
        PageFactory.initElements(webDriver, this);
    }

    public void loginWithNameAndPass(String uid, String password) throws InterruptedException {
        ActionsFinder.sendKeys(userName, uid);
        ActionsFinder.assertEquals(userName, "wesam@wesam.com");
        ActionsFinder.sendKeys(userPassword, password);
        ActionsFinder.contextClick(submitButton);
        ActionsFinder.click(submitButton);
    }
}