package com.company.mobile.tests;

import com.company.mobile.core.BaseTest;
import com.company.mobile.pages.AuthChoicePage;
import com.company.mobile.pages.CatalogPage;
import com.company.mobile.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPositiveTest extends BaseTest {

    @Test
    public void shouldLoginSuccessfully() {

        AuthChoicePage authChoicePage = new AuthChoicePage();
        LoginPage loginPage = authChoicePage.openLogin();

        loginPage.enterPhone("550032490");
        loginPage.enterPassword("market2026");

        loginPage.submit();

        CatalogPage catalogPage = new CatalogPage();

        Assert.assertTrue(
                catalogPage.isOpened(),
                "Catalog page did not open after successful login"
        );
    }
}
