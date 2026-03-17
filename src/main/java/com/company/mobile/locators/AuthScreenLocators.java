package com.company.mobile.locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public final class AuthScreenLocators {

    private AuthScreenLocators() {
    }

    public static final By TITLE =
            AppiumBy.accessibilityId("Авторизация");
}
