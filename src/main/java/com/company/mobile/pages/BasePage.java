package com.company.mobile.pages;

import com.company.mobile.config.Config;
import com.company.mobile.core.DriverManager; // Берём driver из нашего DriverManager
import io.appium.java_client.AppiumDriver; // Базовый Appium driver (пока Android-only, но тип общий)
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait; // Явные ожидания

import java.time.Duration; // Для задания таймаута ожиданий

public abstract class BasePage { // abstract: нельзя создать BasePage напрямую, только наследоваться

    protected final AppiumDriver driver; // driver доступен наследникам (страницам)
    protected final WebDriverWait wait; // wait доступен наследникам для ожиданий элементов

    protected BasePage() { // конструктор вызывается при создании любой Page
        this.driver = DriverManager.getDriver(); // берём текущий driver из ThreadLocal

        // Берём таймаут из config (с возможностью override через -Dexplicit.wait=...)
        int timeout = Integer.parseInt(Config.get("explicit.wait"));

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout)); // базовый таймаут ожиданий (пока фиксированный)
    }

    /**
     * Ждём пока элемент станет видимым.
     * Используется для якорных элементов страницы.
     */
    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Ждём пока элемент станет кликабельным.
     */
    protected WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void type(By locator, String text) {
        WebElement element = waitVisible(locator);
        element.click();
        element.clear();
        element.sendKeys(text);
    }
    /**
     * Быстрая проверка наличия элемента без падения теста.
     */
    protected boolean isPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }
}