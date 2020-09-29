package com.exalt.dataproviderinfra;

import com.exalt.dataproviderinfra.datareader.ConduitLoginTest;
import com.exalt.dataproviderinfra.datareader.ConduitPostNewArticleTest;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;

public final class DataProviderFinder<T> {

    @NotNull
    @DataProvider(name = "Excel")
    public static Object[][] getData(@NotNull Method method) throws IOException {
        if (method.getName().equals("ConduitLoginTest")) {
            return ConduitLoginTest.getCredentials();
        } else if (method.getName().equals("ConduitPostNewArticleTest")) {
            return ConduitPostNewArticleTest.getPosters();
        } else {
            return null;
        }
    }
}
