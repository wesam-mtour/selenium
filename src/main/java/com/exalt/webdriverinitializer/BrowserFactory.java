package com.exalt.webdriverinitializer;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserFactory {
    static final String CHROME_WEB_DRIVER_KEY = "webdriver.chrome.driver";
    static final String CHROME_WEB_DRIVER_VALUE = "C:\\Users\\wesamM\\Downloads\\chromedriver.exe";
    static final String FIREFOX_WEB_DRIVER_KEY = "webdriver.gecko.driver";
    static final String FIREFOX_WEB_DRIVER_VALUE = "C:\\Users\\wesamM\\Downloads\\geckodriver.exe";
    static final String INTERNET_EXPLORER_WEB_DRIVER_KEY = "webdriver.ie.driver";
    static final String INTERNET_EXPLORER_WEB_DRIVER_VALUE = "C:\\Users\\wesamM\\Downloads\\IEDriverServer.exe";
    private static WebDriver webDriver;
    private static final int TIME_OUT = 5;
    private static WebDriverWait wait;

    public static WebDriver startWebDriver(@NotNull String browserName) throws Exception {

        switch (browserName.toLowerCase()) {
            /*
             Check if parameter passed from TestNG is 'chrome'
             */
            case "chrome":
            /*
            The setProperty method enables QAs to set the properties for the desired browser
             to be used in test automation.
            Create chrome instance
             */
                System.setProperty(CHROME_WEB_DRIVER_KEY, CHROME_WEB_DRIVER_VALUE);
                webDriver = new ChromeDriver();
                break;
            /*
             Check if parameter passed from TestNG is 'firefox'
             */
            case "firefox":
            /*
            The setProperty method enables QAs to set the properties for the desired browser
             to be used in test automation.
            Create firefox instance
             */
                System.setProperty(FIREFOX_WEB_DRIVER_KEY, FIREFOX_WEB_DRIVER_VALUE);
                webDriver = new FirefoxDriver();
                break;
            /*
             Check if parameter passed from TestNG is 'ie'
             */
            case "ie":
            /*
            The setProperty method enables QAs to set the properties for the desired browser
             to be used in test automation.
            Create ie instance
             */
                System.setProperty(INTERNET_EXPLORER_WEB_DRIVER_KEY, INTERNET_EXPLORER_WEB_DRIVER_VALUE);
                webDriver = new InternetExplorerDriver();
                break;
            default:
                System.out.println("Browser not Found ");
        }
//        openUrl(url);
        setWaitInstance();
        return webDriver;
    }

    public static void openUrl(String url) {
        webDriver.get(url);
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public static WebDriverWait getWaitInstance() {
        return wait;
    }

    private static void setWaitInstance() {
        wait = new WebDriverWait(webDriver, TIME_OUT);
    }
}