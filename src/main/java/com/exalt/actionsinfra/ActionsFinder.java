package com.exalt.actionsinfra;

import com.exalt.webdriverinitializer.BrowserFactory;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public final class ActionsFinder<T> {

    private static WebDriver webDriver = BrowserFactory.getWebDriver();
    private static final int TIME_OUT = 5;
    private static WebDriverWait wait = new WebDriverWait(webDriver, TIME_OUT);

    public static void click(@NotNull WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    public static void clear(@NotNull WebElement webElement) {
        //wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.clear();
    }

    public static boolean isDisplayed(@NotNull WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.isDisplayed();
    }

    public static boolean isSelected(@NotNull WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeSelected(webElement));
        return webElement.isSelected();
    }

    public static String getText(@NotNull WebElement webElement) {
        wait.until(ExpectedConditions.textToBePresentInElement(webElement, webElement.getText()));
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
        Actions actions = new Actions(webDriver);
        actions.doubleClick(webElement).perform();
    }

    /*
    Shifts the mouse pointer to the center of the element
     */
    public static void moveToElement(WebElement webElement) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement).perform();
    }

    /*
     Performs right-click on the mouse
     */
    public static void contextClick(WebElement webElement) {
        Actions actions = new Actions(webDriver);
        actions.contextClick(webElement);
    }

    public static void assertFalse(boolean element) {
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

    public static void assertEquals(@NotNull String actualValue, String expectedValue) {
        Assert.assertEquals(actualValue, expectedValue);
    }

    public static void assertNull(@NotNull WebElement webElement) {
        Assert.assertNull(webElement.getAttribute("value"));
    }

    public static String getTitle() {
        return webDriver.getTitle();
    }


}