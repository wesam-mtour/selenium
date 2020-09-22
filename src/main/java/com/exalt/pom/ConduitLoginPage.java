package com.exalt.pom;

import com.exalt.infra.ActionsFinder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ConduitLoginPage {

    private WebDriver webDriver;
    /*
    Used to mark a field on a Page Object to indicate an alternative mechanism for locating the element or
     a list of elements. Used in conjunction with PageFactory this allows users to quickly and easily create PageObjects
     */
    @FindBy(how = How.XPATH, using = "/html/body/div/div/div/div/div/div/form/fieldset/fieldset[2]/input")
    WebElement email;

    @FindBy(how = How.XPATH, using = "/html/body/div/div/div/div/div/div/form/fieldset/fieldset[3]/input")
    WebElement password;

    @FindBy(how = How.XPATH, using = "/html/body/div/div/div/div/div/div/form/fieldset/button")
    WebElement signInButton;

    public ConduitLoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        //This initElements method will create all WebElements
        PageFactory.initElements(webDriver, this);
    }

    public void loginWithEmailAndPassword(String email, String password) throws InterruptedException {
        ActionsFinder.sendKeys(this.email,email);
        //ActionsFinder.assertEquals(this.email, "wiasm.mtour@gmail.com");
        ActionsFinder.sendKeys(this.password, password);
        ActionsFinder.contextClick(signInButton);
        ActionsFinder.click(signInButton);
    }

}
