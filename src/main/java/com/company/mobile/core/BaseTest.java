package com.company.mobile.core; // Пакет как у DriverManager, чтобы core-слой был единым и понятным

import org.testng.annotations.AfterMethod; // Аннотация TestNG: метод будет запускаться после КАЖДОГО @Test
import org.testng.annotations.BeforeMethod; // Аннотация TestNG: метод будет запускаться перед КАЖДЫМ @Test

public abstract class BaseTest { // abstract: этот класс не для прямого запуска, а как база для тестов

    private static final boolean RUN_MOBILE = Boolean.getBoolean("runMobile");
    // Boolean.getBoolean("runMobile") читает JVM property: -DrunMobile=true
    // По умолчанию false — значит тесты будут работать как сейчас, без Appium.

    @BeforeMethod // TestNG lifecycle: выполняется перед каждым тестом (каждым @Test)
    public void setUp() { // Название стандартное: “подготовка окружения теста”
        // Лог в консоль, чтобы увидеть что lifecycle реально вызывается
        System.out.println("=== BEFORE METHOD: setUp() ===");

        if (!RUN_MOBILE) { // Если мобильный запуск выключен
            System.out.println("=== Mobile driver creation SKIPPED (runMobile=false) ==="); // Объясняем почему драйвер не создаём
            return; // Выходим, тесты остаются “smoke-only”
        }

        System.out.println("=== Creating AndroidDriver... ==="); // Логируем намерение создать драйвер
        DriverManager.setDriver(DriverFactory.createAndroidDriver()); // Создаём драйвер и сохраняем в ThreadLocal

        String currentPackage =
                ((io.appium.java_client.android.AndroidDriver) DriverManager.getDriver())
                        .getCurrentPackage();
        // Получаем текущий пакет активного приложения на устройстве

        System.out.println("=== Current package: " + currentPackage + " ===");
        // Логируем для наглядности

        if (!"kg.mseller.app".equals(currentPackage)) {
            // Если открылось не-то приложение — падаем сразу
            throw new RuntimeException("Unexpected package launched: " + currentPackage);
        }
    }

    @AfterMethod(alwaysRun = true) // alwaysRun=true: выполнится даже если тест упал/скипнулся (чтобы чистить драйвер)
    public void tearDown() { // Название стандартное: “очистка после теста”
        // Лог перед закрытием драйвера
        System.out.println("=== AFTER METHOD: tearDown() ===");

        DriverManager.quitDriver(); // Гарантированно закрываем сессию/драйвер, чтобы тесты не влияли друг на друга
    }
}

