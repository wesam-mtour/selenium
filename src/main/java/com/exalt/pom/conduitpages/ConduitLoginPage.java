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
    private String testingPassword = "123456789";
    private String testingErrorMessage = "email or password is invalid";

    @FindBy(how = How.CSS, using = "input[type=\"email\"]")
    public WebElement email;

    @FindBy(how = How.XPATH, using = "/html/head/title")
    public WebElement titlee;

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
        ActionsFinder.sendKeys(this.email, email);
        ActionsFinder.sendKeys(this.password, password);
        ActionsFinder.click(signInButton);
    }

    public void logIn() {
        ActionsFinder.sendKeys(this.email, testingEmail);
        ActionsFinder.sendKeys(this.password, testingPassword);
        ActionsFinder.click(signInButton);
    }

    public void clickSignInButton() {
        ActionsFinder.click(this.signInButton);
    }


}
