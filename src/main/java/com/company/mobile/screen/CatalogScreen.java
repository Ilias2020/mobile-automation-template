package com.company.mobile.screen;

import io.appium.java_client.AppiumDriver;
import com.company.mobile.locators.CatalogScreenLocators;
import io.qameta.allure.Step;

public class CatalogScreen extends BaseScreen {

    public CatalogScreen(AppiumDriver driver) {
        super(driver);
    }

    @Step("Проверка открытия экрана Каталога")
    public boolean isOpened() {
        return isElementDisplayed(CatalogScreenLocators.TITLE);
    }

    public void waitUntilOpened() {
        waitUntilElementIsVisible(CatalogScreenLocators.TITLE);
    }
}