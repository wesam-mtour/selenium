package com.exalt.tests.conduittests.settings;

import com.exalt.infra.dataprovider.ExcelDataProvider;
import com.exalt.pom.conduitpages.ConduitHomePage;
import com.exalt.pom.conduitpages.ConduitLoginPage;
import com.exalt.pom.conduitpages.ConduitProfilePage;
import com.exalt.pom.conduitpages.ConduitSettingsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;


@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
public class BaseSettings {

    protected WebDriver webDriver;
    protected final String WEB_DRIVER_URL = "https://demo.productionready.io/#/login";
    protected ConduitLoginPage conduitLoginPage;
    protected ConduitHomePage conduitHomePage;
    protected ConduitSettingsPage conduitSettingsPage;
    protected ConduitProfilePage conduitProfilePage;
    protected WebDriverWait wait;
    protected final int TIME_OUT = 5;


    protected void setup(String browser) throws Exception {
    }


    protected void beforeMethod() {

    }


    protected void afterMethod() {

    }


    protected void afterClass() {

    }
}

