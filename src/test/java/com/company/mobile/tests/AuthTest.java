package com.company.mobile.tests;

import com.company.mobile.screen.CatalogScreen;
import com.company.mobile.utils.UtilsMethod;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.company.mobile.screen.WelcomeScreen;
import com.company.mobile.screen.AuthScreen;

public class AuthTest extends BaseTest {

    @Test
    public void userCanLoginWithValidData() {
        WelcomeScreen welcomeScreen = new WelcomeScreen(driver);

        Assert.assertTrue(welcomeScreen.isOpened(), "Welcome screen is not opened");

        welcomeScreen.clickLogin();

        AuthScreen authScreen = new AuthScreen(driver);
        Assert.assertTrue(authScreen.isOpened(), "Auth screen is not opened");

        authScreen.enterPhone(UtilsMethod.get("user.phone"));
        authScreen.enterPassword(UtilsMethod.get("user.password"));

        authScreen.clickLogin();

        CatalogScreen catalogScreen = new CatalogScreen(driver);
        catalogScreen.waitUntilOpened();
        Assert.assertTrue(catalogScreen.isOpened(), "Catalog screen is not opened");
    }
}