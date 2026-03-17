package com.company.mobile.tests;

import com.company.mobile.screen.AuthScreen;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.company.mobile.screen.WelcomeScreen;

public class WelcomeScreenTest extends BaseTest {

    @Test
    public void userCanTapLoginButton() {
        WelcomeScreen welcomeScreen = new WelcomeScreen(driver);

        Assert.assertTrue(welcomeScreen.isOpened(), "Welcome screen is not opened");

        welcomeScreen.clickLogin();

        AuthScreen authScreen = new AuthScreen(driver);
        Assert.assertTrue(authScreen.isOpened(), "Auth screen is not opened");
    }
}
