package com.exalt.pom.conduitpages;

import com.exalt.infra.actions.Actionsf;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.WebDriverWait;

public class ConduitLoginPage {
    private WebDriver webDriver;
    private WebDriverWait wait;
    private final int TIME_OUT = 5;

    private String testingEmail = "wiasm.mtour@gmail.com";
    private String testingPassword = "123456789";
    private String testingErrorMessage = "email or password is invalid";

    @FindBy(how = How.CSS, using = "input[type=\"email\"]")
    public WebElement email;

    @FindBy(how = How.CSS, using = "input[type=\"password\"]")
    public WebElement password;

    @FindBy(how = How.CSS, using = "button[type=\"submit\"]")
    public WebElement signInButton;

    @FindBy(how = How.CSS, using = "li[ng-repeat=\"error in errors\"]")
    public WebElement errorMessage;

    public ConduitLoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, TIME_OUT);
        /*
        This initElements method will create all WebElements
         */
        PageFactory.initElements(webDriver, this);
    }

    public void logIn(String email, String password) {
        Actionsf.sendKeys(this.email, email);
        Actionsf.sendKeys(this.password, password);
        Actionsf.click(signInButton);
    }

    public void logIn() {
        Actionsf.sendKeys(this.email, testingEmail);
        Actionsf.sendKeys(this.password, testingPassword);
        Actionsf.click(signInButton);
    }

    public void clickSignInButton() {
        Actionsf.click(this.signInButton);
    }


}
