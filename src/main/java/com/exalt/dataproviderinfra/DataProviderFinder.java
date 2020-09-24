package com.exalt.dataproviderinfra;

import com.exalt.dataproviderinfra.datareader.ConduitLoginPageDataReader;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;

public final class DataProviderFinder<T> {

    @NotNull
    @DataProvider(name = "Excel")
    public static Object[][] getConduitLoginPageTestData(@NotNull Method method) throws IOException {
        if (method.getName().equals("ConduitLoginTest")) {
            return ConduitLoginPageDataReader.getCredentials();
        } else {
            return null;
        }
    }
}
