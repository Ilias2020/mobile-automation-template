package com.company.mobile.tests.auth;

import com.company.mobile.core.BaseTest;
import com.company.mobile.pages.AuthChoicePage;
import com.company.mobile.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.company.mobile.core.DriverManager;

public class LoginNegativeTest extends BaseTest {

    @Test
    public void shouldNotLoginWithWrongCredentials() {
        AuthChoicePage authChoicePage = new AuthChoicePage();
        LoginPage loginPage = authChoicePage.openLogin();
        loginPage.enterPhone("500000000");
        loginPage.enterPassword("wrongpass");
        Assert.assertTrue(loginPage.isPasswordEntered(),
                "Password was not entered into password field");
        //loginPage.triggerValidation();
        Assert.assertTrue(loginPage.isSubmitEnabled(),
                "Login button is not enabled before submit. State: " + loginPage.getSubmitState());
        System.out.println("[Test] Before submit: " + loginPage.getSubmitState());
        loginPage.submit();
        System.out.println("[Test] okVisible=" + loginPage.isOkButtonDisplayed());
        System.out.println("[Test] pageSourceContainsError=" +
                DriverManager.getDriver().getPageSource().contains("Невозможно войти"));
        System.out.println("[Test] After submit: errorVisible=" + loginPage.isErrorDialogDisplayed());
        Assert.assertTrue(loginPage.isErrorDialogDisplayed(),
                "Login error dialog was not displayed");
    }
}
