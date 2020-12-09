package com.exalt.tests.APItest.login;

import com.exalt.infra.APItest.RestAPIs;
import com.exalt.infra.dataprovider.ExcelDataProvider;

import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(dataProvider = "Excel", dataProviderClass = ExcelDataProvider.class)
public class BaseLogin {

    RestAPIs restAPIs = new RestAPIs();
    protected final String LOGIN_URL = "https://conduit.productionready.io/api/users/login";
    protected JSONObject response;

    @BeforeClass
    public void setup() {

    }

    protected void beforeMethod() {
    }

    protected void afterMethod() {

    }

    @AfterClass
    protected void tearDown() {
    }
}
