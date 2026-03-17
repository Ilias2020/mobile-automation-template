package com.company.mobile.screen;

import io.appium.java_client.AppiumDriver;
import com.company.mobile.locators.AuthScreenLocators;
import io.qameta.allure.Step;

public class AuthScreen extends BaseScreen {

    public AuthScreen(AppiumDriver driver) {
        super(driver);
    }

    @Step("Проверка открытия экрана авторизации")
    public boolean isOpened() {
        return isElementDisplayed(AuthScreenLocators.TITLE);
    }
}