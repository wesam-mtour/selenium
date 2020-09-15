package com.exalt.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SeleniumTest {

    WebDriver webDriver;

    /*
    As the name suggests, the setProperty method enables QAs to set the properties for the desired browser
     to be used in test automation.
     The setProperty method has two attributes – “propertyName” and “value.” The propertyName represents the name
     of the browser-specific driver, and the value points to the path of that browser driver.
     */
    @BeforeClass
    void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\WesamM\\Downloads\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("https://www.facebook.com/");

    }

    @Test
    void titleTest() {
        String title = webDriver.getTitle();
        Assert.assertEquals("Facebook - Log In or Sign Up", title, "not matched");
    }

    @Test
    void logoTest() {
        WebElement logo = webDriver.findElement(By.cssSelector("img[src='https://static.xx.fbcdn.net/rsrc.php/y8/r/dF5SId3UHWd.svg']"));
        Assert.assertTrue(logo.isDisplayed());
    }

    @AfterClass
    void endSession(){
        webDriver.quit();
    }

}
