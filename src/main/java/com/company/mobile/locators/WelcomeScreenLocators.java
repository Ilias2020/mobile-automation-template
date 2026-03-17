package com.company.mobile.locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public final class WelcomeScreenLocators {

    private WelcomeScreenLocators() {
    }

    public static final By LOGIN_BUTTON =
            AppiumBy.accessibilityId("Войти");

    public static final By REGISTER_BUTTON =
            AppiumBy.accessibilityId("Зарегистрироваться");
}
