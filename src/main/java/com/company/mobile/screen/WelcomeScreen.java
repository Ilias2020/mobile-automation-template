package com.company.mobile.screen;

import io.qameta.allure.Step;
import io.appium.java_client.AppiumDriver;
import com.company.mobile.locators.WelcomeScreenLocators;

public class WelcomeScreen extends BaseScreen {

    public WelcomeScreen(AppiumDriver driver) {
        super(driver);
    }

    @Step("Проверка открытия Welcome экрана")
    public boolean isOpened() {
        return isElementDisplayed(WelcomeScreenLocators.LOGIN_BUTTON)
                && isElementDisplayed(WelcomeScreenLocators.REGISTER_BUTTON);
    }

    @Step("Клик по кнопке Войти")
    public void clickLogin() {
        click(WelcomeScreenLocators.LOGIN_BUTTON);
    }

    @Step("клик по кнопке Зарегистрироваться")
    public void clickRegister() {
        click(WelcomeScreenLocators.REGISTER_BUTTON);
    }
}