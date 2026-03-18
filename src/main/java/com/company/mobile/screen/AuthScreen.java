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

    @Step("Ввод номера телефона")
    public void enterPhone(String phone) {
        click(AuthScreenLocators.PHONE_INPUT);
        driver.findElement(AuthScreenLocators.PHONE_INPUT).sendKeys(phone);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        click(AuthScreenLocators.PASSWORD_INPUT);
        driver.findElement(AuthScreenLocators.PASSWORD_INPUT).sendKeys(password);
    }

    @Step("Нажатие кнопки Войти")
    public void clickLogin() {
        waitUntilElementIsClickable(AuthScreenLocators.LOGIN_BUTTON);
        driver.findElement(AuthScreenLocators.LOGIN_BUTTON).click();
    }
}