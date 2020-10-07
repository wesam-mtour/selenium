package com.exalt.infra.utils;

import com.exalt.infra.actions.Actionsf;
import com.exalt.pom.conduitpages.ConduitHomePage;
import com.exalt.webdriverinitializer.BrowserFactory;
import org.openqa.selenium.WebDriver;

public final class Constants {
    private static WebDriver webDriver = BrowserFactory.getWebDriver();
    private static ConduitHomePage conduitHomePage = new ConduitHomePage(webDriver);
    public final static String HOME_PAGE = "Home — Conduit";
    public final static String EDITOR_PAGE = "Editor — Conduit";
    public final static String SIGN_IN_PAGE = "Sign in — Conduit";
    public final static String SETTINGS_PAGE = "Settings — Conduit";
    public final static String USER_PAGE = "@" + Actionsf.getText(conduitHomePage.profileLink) + " — Conduit";


}
