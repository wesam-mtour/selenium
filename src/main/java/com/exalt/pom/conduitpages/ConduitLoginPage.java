package com.exalt.pom.conduitpages;

import com.exalt.infra.actions.ActionsFinder;
import org.omg.PortableInterceptor.ACTIVE;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static com.exalt.infra.utils.Constants.HOME_PAGE;
import static com.exalt.infra.utils.Constants.SIGN_IN_PAGE;

public class ConduitLoginPage {
    private WebDriver webDriver;
    private WebDriverWait wait;
    private final int TIME_OUT = 5;

    private String testingEmail = "wiasm.mtour@gmail.com";
    private String testingPassword = "11223344";
    private String testingErrorMessage = "email or password is invalid";

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

    @FindBy(how = How.CSS, using = "li[ng-repeat=\"error in errors\"]")
    WebElement errorMessage;

    public ConduitLoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, TIME_OUT);
        /*
        This initElements method will create all WebElements
         */
        PageFactory.initElements(webDriver, this);
    }

    public void loginWithInvalidCredentials(String email, String password, String expectedErrorMessage) {
        ActionsFinder.sendKeys(this.email, email);
        ActionsFinder.sendKeys(this.password, password);
        ActionsFinder.click(signInButton);
        boolean i =ActionsFinder.isDisplayed(this.errorMessage);

    }

    public void loginWithValidCredentials(String email, String password) throws InterruptedException {
        ActionsFinder.sendKeys(this.email, email);
        ActionsFinder.sendKeys(this.password, password);
        ActionsFinder.click(signInButton);
    }

    public void logIn(String email , String password) {
        ActionsFinder.sendKeys(this.email, email);
        ActionsFinder.sendKeys(this.password, password);
        ActionsFinder.click(signInButton);
        ActionsFinder.waitTitleToBe(HOME_PAGE, wait);
    }


    public WebElement getEmail() {
        return this.email;
    }

    public WebElement getPassword() {
        return this.password;
    }

    public WebElement getSignInButton() {
        return this.signInButton;
    }

    public WebElement getErrorMessage() {
        return this.errorMessage;
    }

    public void setTestingEmail(String testingEmail) {
        this.testingEmail = testingEmail;
    }

    public void setTestingPassword(String testingPassword) {
        this.testingPassword = testingPassword;
    }

}
