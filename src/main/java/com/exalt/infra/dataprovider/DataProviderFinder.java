package com.exalt.infra.dataprovider;

import com.exalt.infra.dataprovider.datareader.ConduitLoginTest;
import com.exalt.infra.dataprovider.datareader.ConduitPostNewArticleTest;
import com.exalt.infra.dataprovider.datareader.ConduitSetNewPasswordTest;
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
        } else if (method.getName().equals("ConduitSetNewPasswordTest")){
            return ConduitSetNewPasswordTest.getNewPasswords();
        }
        else{
            return null;
        }
    }
}
