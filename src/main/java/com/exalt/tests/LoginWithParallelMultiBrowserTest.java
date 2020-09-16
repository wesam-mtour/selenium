package com.exalt.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.exalt.pom.BrowserFactory;

import com.exalt.pom.LoginPage;
import com.exalt.report.ReporterListener;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class LoginWithParallelMultiBrowserTest {


    final String WEB_DRIVER_URL = "https://senglehardt.com/demo/no_boundaries/loginmanager/";
    private WebDriver webDriver;
    private LoginPage loginPage;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception {

        webDriver = BrowserFactory.startWebDriver(browser, WEB_DRIVER_URL);
    }

    @Test
    public void UserLoginTest() throws InterruptedException {

        loginPage = new LoginPage(webDriver);
        loginPage.loginWithNameAndPass("wesam@wesam.com", "wesam");
    }

    @AfterTest
    public void tearDown() {

        webDriver.quit();

    }
}
