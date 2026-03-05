package com.company.mobile.core;

// Импортируем базовый AppiumDriver.
// Это общий тип для AndroidDriver / IOSDriver.
import io.appium.java_client.AppiumDriver;

public final class DriverManager {

    // ThreadLocal хранит отдельный экземпляр драйвера для каждого потока.
    // Сейчас у нас один поток, но это задел под будущую параллельность.
    private static final ThreadLocal<AppiumDriver> DRIVER = new ThreadLocal<>();

    // Приватный конструктор запрещает создание объекта DriverManager.
    // Этот класс используется только как utility (статические методы).
    private DriverManager() {}

    // Возвращает текущий драйвер из ThreadLocal.
    // Любой тест или страница будет получать драйвер через этот метод.
    public static AppiumDriver getDriver() {
        return DRIVER.get();
    }

    // Устанавливает драйвер в ThreadLocal.
    // Мы вызовем этот метод в BaseTest после создания драйвера.
    public static void setDriver(AppiumDriver driver) {
        DRIVER.set(driver);
    }

    // Корректно завершает сессию драйвера.
    // 1) Проверяем, что драйвер вообще существует.
    // 2) Закрываем сессию через quit().
    // 3) Удаляем драйвер из ThreadLocal (важно для очистки памяти).
    public static void quitDriver() {
        AppiumDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}