package com.company.mobile.locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public final class AuthScreenLocators {

    private AuthScreenLocators() {
    }

    public static final By TITLE =
            AppiumBy.accessibilityId("Авторизация");

    public static final By PHONE_INPUT =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().className(\"android.widget.EditText\").instance(0)"
            );

    public static final By PASSWORD_INPUT =
            AppiumBy.androidUIAutomator(
                    "new UiSelector().className(\"android.widget.EditText\").instance(1)"
            );

    public static final By LOGIN_BUTTON =
            AppiumBy.accessibilityId("Войти");

    public static final By MBANK_LOGIN_BUTTON =
            AppiumBy.accessibilityId("Вход через MBANK ID");
}
