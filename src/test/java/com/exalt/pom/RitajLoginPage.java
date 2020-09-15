package com.exalt.pom;

import com.exalt.infra.ActionsFinder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

public class RitajLoginPage {

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

    public RitajLoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        //This initElements method will create all WebElements
        PageFactory.initElements(webDriver, this);
    }

    public void loginWithNameAndPass(String uid, String password) {
        ActionsFinder.sendKeys(userName, uid);
        ActionsFinder.assertEquals(userName, "wesam@wesam.com");
        ActionsFinder.sendKeys(userPassword, password);
        ActionsFinder.contextClick(submitButton);
        ActionsFinder.click(submitButton);
    }
}
