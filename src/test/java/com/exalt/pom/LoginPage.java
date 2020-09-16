package com.exalt.pom;

import com.exalt.infra.ActionsFinder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

<<<<<<< HEAD:src/test/java/com/exalt/pom/RitajLoginPage.java
public class RitajLoginPage {

    WebDriver webDriver;
    Logger logger = Logger.getLogger(RitajLoginPage.class);
=======
public class LoginPage {

    private WebDriver webDriver;
>>>>>>> a9af8ce8395f98c16e1e07af631a80f3f7a681ee:src/test/java/com/exalt/pom/LoginPage.java
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

    public void loginWithNameAndPass(String uid, String password) {
        ActionsFinder.sendKeys(userName, uid);
<<<<<<< HEAD:src/test/java/com/exalt/pom/RitajLoginPage.java
        ActionsFinder.assertEquals(userName, "1160508");


        Actions a = new Actions(webDriver);
        a.contextClick(userName).perform();


=======
        ActionsFinder.assertEquals(userName, "wesam@wesam.com");
>>>>>>> a9af8ce8395f98c16e1e07af631a80f3f7a681ee:src/test/java/com/exalt/pom/LoginPage.java
        ActionsFinder.sendKeys(userPassword, password);
        ActionsFinder.contextClick(submitButton);
        ActionsFinder.click(submitButton);
    }
}
