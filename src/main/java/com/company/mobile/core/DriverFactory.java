package com.company.mobile.core; // Кладём рядом с BaseTest/DriverManager, чтобы core был в одном месте


import io.appium.java_client.android.AndroidDriver; // Класс драйвера для Android (Appium Java Client)
import io.appium.java_client.android.options.UiAutomator2Options; // Правильные W3C options для UiAutomator2

import java.io.File; // Нужен чтобы корректно получить абсолютный путь к apk
import java.net.URL; // URL для адреса Appium сервера

import com.company.mobile.config.Config;

public final class DriverFactory { // final: утилитарный класс, не предполагает наследования

    private DriverFactory() { // приватный конструктор: запрещаем создавать экземпляры
        // Ничего не делаем — это просто защита от new DriverFactory()
    }

    public static AndroidDriver createAndroidDriver() { // Единая точка создания AndroidDriver
        try { // try: URL(...) может выбросить исключение
            // URL локального Appium сервера (у тебя он уже запущен)
            URL appiumServerUrl = new URL(Config.appiumUrl());

            File app = new File(Config.appPath()); // Твой apk

            UiAutomator2Options options = new UiAutomator2Options(); // W3C options вместо DesiredCapabilities
            options.setPlatformName("Android"); // Платформа Android
            options.setAutomationName("UiAutomator2"); // Драйвер автоматизации
            options.setDeviceName(Config.deviceName()); // Имя устройства (эмулятор)
            options.setApp(app.getAbsolutePath()); // Путь к apk (Appium установит и запустит)
            options.autoGrantPermissions(); // Автоматически выдать разрешения приложению при установке

            return new AndroidDriver(appiumServerUrl, options); // Создаём сессию через W3C options


        } catch (Exception e) { // Ловим любые проблемы (URL, создание драйвера, и т.д.)
            throw new RuntimeException("Failed to create AndroidDriver", e); // Пробрасываем как runtime, чтобы тест падал явно
        }
    }
}