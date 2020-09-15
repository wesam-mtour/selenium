package com.exalt.pom;

import com.exalt.infra.ActionsFinder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import java.lang.reflect.AccessibleObject;
import java.util.concurrent.TimeUnit;

public class RitajLoginPage {

    WebDriver webDriver;
    Logger logger = Logger.getLogger(RitajLoginPage.class);
    /*
    Used to mark a field on a Page Object to indicate an alternative mechanism for locating the element or
     a list of elements. Used in conjunction with PageFactory this allows users to quickly and easily create PageObjects
     */
    @FindBy(how = How.NAME, using = "username")
    WebElement userName;

    @FindBy(how = How.NAME, using = "password")
    WebElement userPassword;

    @FindBy(how = How.NAME, using = "formbutton:ok")
    WebElement submitButton;

    public RitajLoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        //This initElements method will create all WebElements
        PageFactory.initElements(webDriver, this);
    }

    public void loginWithNameAndPass(String uid, String password) {
        ActionsFinder.sendKeys(userName, uid);
        ActionsFinder.assertEquals(userName,"1160508");



        Actions a =new Actions(webDriver);
        a.contextClick(userName).perform();


        ActionsFinder.sendKeys(userPassword, password);

        ActionsFinder.click(submitButton);
    }
}
