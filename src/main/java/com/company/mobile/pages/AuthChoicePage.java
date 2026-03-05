package com.company.mobile.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

/**
 * Экран выбора: Login / Register.
 * Отвечает только за этот экран.
 */
public class AuthChoicePage extends BasePage {

    // Якорь страницы — кнопка Login (content-desc="Login")
    private final By loginButton = AppiumBy.accessibilityId("Login");

    /**
     * Проверяем, что экран выбора открыт.
     */
    public boolean isOpened() {
        try {
            waitVisible(loginButton); // ждём якорь до explicit.wait секунд
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Нажимаем Login и переходим на LoginPage.
     */
    public LoginPage openLogin() {
        waitClickable(loginButton).click();
        return new LoginPage();
    }
}