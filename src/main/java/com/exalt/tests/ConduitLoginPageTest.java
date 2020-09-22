package com.exalt.tests;

import com.exalt.dataprovider.ExcelReader;
import com.exalt.pom.BrowserFactory;
import com.exalt.pom.ConduitLoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class ConduitLoginPageTest {
    final String XLSX_FILE_PATH = "C:\\Users\\WesamM\\Desktop\\credentials.xlsx";
    final int sheetNumber = 0;
    final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    private WebDriver webDriver;
    private ConduitLoginPage conduitLoginPage;

    @DataProvider(name = "credentials")
    public Object[][] Authentication() throws Exception {
        return ExcelReader.getCredentials(XLSX_FILE_PATH, sheetNumber);
    }

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        webDriver = BrowserFactory.startWebDriver(browser, WEB_DRIVER_URL);
    }

    @Test(dataProvider = "credentials")
    public void ConduitLoginTest(String email, String password) throws InterruptedException {
        conduitLoginPage = new ConduitLoginPage(webDriver);
        conduitLoginPage.loginWithEmailAndPassword(email, password);
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }
}


