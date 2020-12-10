package com.exalt.tests.APItest.article;

import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
public class BaseArticle {


    @BeforeClass
    @Parameters("browser")
    protected void setup(String browser) {

    }

    protected void beforeMethod() {

    }

    protected void afterMethod() {

    }

    @AfterClass
    protected void tearDown() {
    }


}
