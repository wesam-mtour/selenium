package com.exalt.infra.actions;

import com.exalt.webdriverinitializer.BrowserFactory;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Actionsf {

    private static WebDriver webDriver = BrowserFactory.getWebDriver();
    public static WebDriverWait wait = BrowserFactory.getWaitInstance();

    public static void click(@NotNull WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();

    }

    public static void clear(@NotNull WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.clear();
    }

    public static boolean isDisplayed(@NotNull WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.isDisplayed();
    }

    public static void isNotDisplayed(@NotNull WebElement webElement) {
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public static boolean isSelected(@NotNull WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeSelected(webElement));
        return webElement.isSelected();
    }

    public static String getText(@NotNull WebElement webElement, String expectedValue) {
        wait.until(ExpectedConditions.textToBePresentInElement(webElement, expectedValue));
        return webElement.getText();
    }


    public static String getText(@NotNull WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.getText();
    }

    public static void sendKeys(@NotNull WebElement webElement, String string) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(string);
    }

    /*
    Performs double click on the element
     */
    public static void doubleClick(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(webDriver);
        actions.doubleClick(webElement).perform();
    }

    /*
    Shifts the mouse pointer to the center of the element
     */
    public static void moveToElement(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(webDriver);
        actions.moveToElement(webElement).perform();
    }

    /*
     Performs right-click on the mouse
     */
    public static void contextClick(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(webDriver);
        actions.contextClick(webElement);
    }

    public static void assertFalse(boolean element) {
        // wait.until(Exp)
        Assert.assertFalse(element);
    }

    public static void assertTrue(boolean element) {
        Assert.assertTrue(element);
    }

    public static void assertNotEquals(@NotNull WebElement webElement, String expectedValue) {
        Assert.assertNotEquals(webElement.getAttribute("value"), expectedValue);
    }

    public static void assertNotEquals(@NotNull String actualValue, String expectedValue) {
        Assert.assertNotEquals(actualValue, expectedValue);
    }

    public static void assertEquals(@NotNull WebElement webElement, String expectedValue) {
        Assert.assertEquals(webElement.getAttribute("value"), expectedValue);
    }

    public static void assertEquals(@NotNull int actualValue, int expectedValue) {
        Assert.assertEquals(actualValue, expectedValue);
    }

    public static void assertEquals(@NotNull String actualValue, String expectedValue) {
        Assert.assertEquals(actualValue, expectedValue);
    }

    public static void assertNull(@NotNull WebElement webElement) {
        Assert.assertNull(webElement.getAttribute("value"));
    }

    public static String getTitle() throws InterruptedException {
        return webDriver.getTitle();
    }

    public static void waitTitleToBe(String expectedTitle, @NotNull WebDriverWait wait1) {
        wait1.until(ExpectedConditions.titleIs(expectedTitle));
    }

    public static void waitTitleToBe(String expectedTitle) {
        wait.toString();
        wait.until(ExpectedConditions.titleIs(expectedTitle));
//        return webDriver.getTitle();
    }

}