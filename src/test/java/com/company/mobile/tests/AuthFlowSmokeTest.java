package com.company.mobile.tests;

import com.company.mobile.core.BaseTest;
import com.company.mobile.pages.AuthChoicePage;
import com.company.mobile.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Smoke test:
 * Проверяем переход с экрана выбора на экран авторизации.
 */
public class AuthFlowSmokeTest extends BaseTest {

    @Test
    public void shouldOpenLoginScreenFromAuthChoice() {

        // 1. Создаём страницу выбора
        AuthChoicePage authChoicePage = new AuthChoicePage();

        // 2. Проверяем, что экран выбора открыт
        Assert.assertTrue(authChoicePage.isOpened(),
                "AuthChoicePage is not opened");

        // 3. Нажимаем Login
        LoginPage loginPage = authChoicePage.openLogin();

        // 4. Проверяем, что открылся экран авторизации
        Assert.assertTrue(loginPage.isOpened(),
                "LoginPage is not opened");
    }
}